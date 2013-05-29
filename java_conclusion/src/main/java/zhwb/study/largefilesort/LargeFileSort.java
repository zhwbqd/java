
package zhwb.study.largefilesort;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LargeFileSort
{
    /**
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args)
        throws IOException
    {
        String filePath = "C:\\LargeFileSort\\DataFile.csv";
        int size = 100000000;
        int perSize = 1000000;
        long start = System.currentTimeMillis();
        saveData(filePath, size);
        System.out.println("生成大文件：" + (System.currentTimeMillis() - start) + "ms.");
        start = System.currentTimeMillis();
        splitFile(filePath, perSize);
        System.out.println("拆分大文件：" + (System.currentTimeMillis() - start) + "ms.");
        start = System.currentTimeMillis();
        sortPerFile(filePath);
        System.out.println("排序小文件：" + (System.currentTimeMillis() - start) + "ms.");
        start = System.currentTimeMillis();
        mergeFiles(filePath);
        System.out.println("聚合小文件：" + (System.currentTimeMillis() - start) + "ms.");
    }

    public static void saveData(final String filePath, final int num)
        throws IOException
    {
        File file = new File(filePath);
        if (!file.exists())
        {
            file.getParentFile().mkdirs();
        }
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        Random random = new Random();

        for (int i = 0; i < num; i++)
        {
            out.write(String.valueOf(random.nextInt(Integer.MAX_VALUE)));
            out.newLine();
        }

        out.flush();
        out.close();
        out = null;
        random = null;
        System.out.println("文件大小为:" + (double)(file.length() / 1024 / 1024) + "M");
        System.out.println("文件大小为:" + (double)(new FileInputStream(file).available() / 1024 / 1024) + "M");
    }

    public static void splitFile(final String filePath, final int perSize)
        throws IOException
    {
        File file = new File(filePath);

        String extension = file.getName().split("\\.")[1];
        File outDir = new File(file.getParentFile(), "out");
        if (!outDir.exists())
        {
            outDir.mkdir();
        }

        BufferedReader in = new BufferedReader(new FileReader(file));
        file = null;
        String line = null;
        int n = 0;
        String fileName = new StringBuilder(String.valueOf(n)).append(".").append(extension).toString();
        extension = null;
        BufferedWriter out = new BufferedWriter(new FileWriter(new File(outDir, fileName)));

        int i = 0;
        while ((line = in.readLine()) != null)
        {
            if (i >= perSize)
            {
                i = 0;
                out.flush();
                out.close();
                out = null;
                out = new BufferedWriter(new FileWriter(new File(outDir, fileName.replaceFirst("^\\d+", String.valueOf(++n)))));
            }
            i++;
            out.write(line);
            out.newLine();
        }

        outDir = null;
        fileName = null;
        if (out != null)
        {
            out.flush();
            out.close();
            out = null;
        }
        in.close();
        in = null;
    }

    public static void sortPerFile(final String filePath)
        throws IOException
    {
        File file = new File(filePath);
        File outDir = new File(file.getParentFile(), "out");
        file = null;

        if (outDir.exists())
        {
            BufferedReader in = null;
            BufferedWriter out = null;
            String line = null;
            List<Integer> dataList = new LinkedList<Integer>();
            File[] files = outDir.listFiles();
            outDir = null;

            int size = 0;
            int i = 0;
            for (File smallFile : files)
            {
                in = new BufferedReader(new FileReader(smallFile));
                dataList.clear();

                while ((line = in.readLine()) != null)
                {
                    dataList.add(Integer.valueOf(line));
                }

                in.close();
                in = null;

                Collections.sort(dataList);

                out = new BufferedWriter(new FileWriter(smallFile));
                smallFile = null;

                size = dataList.size();
                while (i < size)
                {
                    out.write(dataList.get(i).toString());
                    out.newLine();
                    dataList.remove(i);
                    size = dataList.size();
                }

                out.flush();
                out.close();
                out = null;
            }

            dataList.clear();
            dataList = null;
            files = null;
        }
    }

    public static void mergeFiles(final String filePath)
        throws IOException
    {
        File file = new File(filePath);
        File outDir = new File(file.getParentFile(), "out");

        if (outDir.exists())
        {
            String extension = file.getName().split("\\.")[1];
            File result = new File(file.getParentFile(), new StringBuilder("ResultFile.").append(extension).toString());
            if (!result.exists())
            {
                result.createNewFile();
            }
            File temp = new File(file.getParentFile(), new StringBuilder("TempFile.").append(extension).toString());
            extension = null;
            if (!temp.exists())
            {
                temp.createNewFile();
            }

            file.delete();

            boolean flag = false;
            BufferedReader smallIn = null;
            BufferedReader largeIn = null;
            String smallLine = null;
            String largeLine = null;
            BufferedWriter out = null;

            File[] files = outDir.listFiles();
            for (File smallFile : files)
            {
                smallIn = new BufferedReader(new FileReader(smallFile));
                if (flag)
                {
                    largeIn = new BufferedReader(new FileReader(temp));
                    out = new BufferedWriter(new FileWriter(result));
                    flag = false;
                }
                else
                {
                    largeIn = new BufferedReader(new FileReader(result));
                    out = new BufferedWriter(new FileWriter(temp));
                    flag = true;
                }

                smallLine = smallIn.readLine();
                largeLine = largeIn.readLine();
                while (smallLine != null && largeLine != null)
                {
                    if (Integer.parseInt(smallLine) < Integer.parseInt(largeLine))
                    {
                        out.write(smallLine);
                        smallLine = smallIn.readLine();
                    }
                    else
                    {
                        out.write(largeLine);
                        largeLine = largeIn.readLine();
                    }
                    out.newLine();
                }

                while (smallLine != null)
                {
                    out.write(smallLine);
                    out.newLine();
                    smallLine = smallIn.readLine();
                }

                while (largeLine != null)
                {
                    out.write(largeLine);
                    out.newLine();
                    largeLine = largeIn.readLine();
                }

                out.flush();
                out.close();
                out = null;
                smallIn.close();
                smallIn = null;
                largeIn.close();
                largeIn = null;
                smallFile.delete();
                smallFile = null;
            }

            files = null;
            outDir.delete();
            outDir = null;
            if (flag)
            {
                temp.renameTo(file);
            }
            else
            {
                result.renameTo(file);
            }
            file = null;
            result.delete();
            result = null;
            temp.delete();
            temp = null;
        }
    }
}

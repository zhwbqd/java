package com.hp.it.common.string;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class StringUtil
{
	public static String streamToString(InputStream input)
	{
		InputStreamReader reader = new InputStreamReader(input);
		StringBuffer buffer = new StringBuffer();
		char buf[] = new char[1024];
		int read = 0;
		try
		{
			while (true)
			{
				read = reader.read(buf, 0, 1024);
				if (read == -1)
				{
					break;
				}
				buffer.append(buf, 0, read);
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		try
		{
			return buffer.toString();
		} finally
		{
			try
			{
				reader.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static String urlToString(URL url)
	{
		String retval = "";
		try
		{
			retval = streamToString(url.openStream());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return retval;
	}
}

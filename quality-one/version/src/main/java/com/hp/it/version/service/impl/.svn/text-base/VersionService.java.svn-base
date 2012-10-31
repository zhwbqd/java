package com.hp.it.version.service.impl;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.hp.it.version.bean.LogEntry;
import com.hp.it.version.service.IVersionService;

public class VersionService implements IVersionService {
	private SVNRepository repository;

	private long latest;

	private final long QUERY_VERSION_THRESHOLD = 100;

	private ISVNAuthenticationManager authenticate(String groupId, String artifactId, String userName, String password,
			boolean usingPrivateKey, String privateKeyFilePath, String passphrase) {
		ISVNAuthenticationManager authManager = null;
		if (usingPrivateKey) {
			if (userName == null || password == null) {
				authManager = SVNWCUtil.createDefaultAuthenticationManager(null, null, null, new File(
						privateKeyFilePath), passphrase, false);
			} else {
				authManager = SVNWCUtil.createDefaultAuthenticationManager(null, userName, password, new File(
						privateKeyFilePath), passphrase, false);
			}
		} else {
			authManager = SVNWCUtil.createDefaultAuthenticationManager(userName, password);
		}
		return authManager;
	}

	private long getLatestRepositoryVersionNum() {
		long latest = -1;
		try {
			SVNDirEntry dir = repository.getDir("", -1, true, new HashSet());
			latest = dir.getRevision();
		} catch (SVNException e) {
			e.printStackTrace();
		}
		return latest;
	}

	public void initialize(String groupId, String artifactId, String urlStr, String userName, String password,
			boolean usingPrivateKey, String privateKeyFilePath, String passphrase) throws SVNException {

		if ("".equalsIgnoreCase(userName) || "".equalsIgnoreCase(password)) {
			userName = null;
			password = null;
		}
		FSRepositoryFactory.setup();
		DAVRepositoryFactory.setup();
		SVNRepositoryFactoryImpl.setup();
		SVNURL svnUrl = SVNURL.parseURIEncoded(urlStr);
		repository = SVNRepositoryFactoryImpl.create(svnUrl);
		repository.setAuthenticationManager(authenticate(groupId, artifactId, userName, password, usingPrivateKey,
				privateKeyFilePath, passphrase));
		latest = getLatestRepositoryVersionNum();
	}

	public void listLogEntries(int startVersion, int endVersion) throws SVNException {
		Collection logEntries = null;
		/* do use the content to match the file */
		logEntries = repository.log(new String[] { "" }, null, startVersion, endVersion, true, true);
		for (Iterator entries = logEntries.iterator(); entries.hasNext();) {
			SVNLogEntry logEntry = (SVNLogEntry) entries.next();
			System.out.println("---------------------------------------------");
			System.out.println("revision: " + logEntry.getRevision());
			System.out.println("author: " + logEntry.getAuthor());
			System.out.println("date: " + logEntry.getDate());
			System.out.println("log message: " + logEntry.getMessage());
			if (logEntry.getChangedPaths().size() > 0) {
				System.out.println();
				System.out.println("changed paths:");
				Set changedPathsSet = logEntry.getChangedPaths().keySet();

				for (Iterator changedPaths = changedPathsSet.iterator(); changedPaths.hasNext();) {
					SVNLogEntryPath entryPath = (SVNLogEntryPath) logEntry.getChangedPaths().get(changedPaths.next());
					System.out.println(" "
							+ entryPath.getType()
							+ " "
							+ entryPath.getPath()
							+ ((entryPath.getCopyPath() != null) ? " (from " + entryPath.getCopyPath() + " revision "
									+ entryPath.getCopyRevision() + ")" : ""));
				}
			}
		}
	}

	/*
	 * ` (non-Javadoc)
	 * 
	 * @see com.hp.it.version.service.impl.IVersionService#
	 * findLatestLogEntriesByQualifiedClassNames(java.util.Set)
	 */
	public Map<String, LogEntry> findLatestLogEntriesByQualifiedClassNames(Set<String> nameSet) {
		Map<String, LogEntry> map = new HashMap<String, LogEntry>();
		for (String name : nameSet) {
			map.put(name, null);
		}
		recursiveResearch(map, latest - QUERY_VERSION_THRESHOLD, latest);
		return map;
	}

	/**
	 * 
	 * @param tmp
	 * @param beginVersion
	 * @param endVersion
	 */
	private void recursiveResearch(Map<String, LogEntry> tmp, long beginVersion, long endVersion) {

		if (beginVersion < 1) {
			beginVersion = 1;
		}
		if (endVersion <= 1) {
			return;
		}

		boolean returnFlag = true;
		for (Map.Entry<String, LogEntry> set : tmp.entrySet()) {
			if (set.getValue() == null) {
				returnFlag = false;
				break;
			}
		}
		/* Check if all the map has been cleared */
		if (returnFlag == true) {
			return;
		}

		try {
			Collection logEntries = repository.log(new String[] { "" }, null, beginVersion, endVersion, true, true);
			Stack<SVNLogEntry> stack = new Stack();
			for (Object entry : logEntries) {
				stack.add((SVNLogEntry) entry);
			}
			while (!stack.isEmpty()) {
				SVNLogEntry logEntry = stack.pop();
				/* Get the version changed paths */
				Set changedPathsSet = logEntry.getChangedPaths().keySet();

				if (logEntry.getChangedPaths().size() > 0) {
					for (Iterator changedPaths = changedPathsSet.iterator(); changedPaths.hasNext();) {
						SVNLogEntryPath entryPath = (SVNLogEntryPath) logEntry.getChangedPaths().get(
								changedPaths.next());
						String path = entryPath.getPath();
						String tmpPath = path.replaceAll("/", ".");
						for (Map.Entry<String, LogEntry> set : tmp.entrySet()) {
							if (set.getValue() == null) {
								if (tmpPath.contains(set.getKey())) {
									LogEntry le = new LogEntry();
									le.setPath(path);
									le.setName(set.getKey());
									le.setAuthor(logEntry.getAuthor());
									le.setDate(logEntry.getDate());
									le.setMessage(logEntry.getMessage());
									le.setReversion(logEntry.getRevision());
									set.setValue(le);
								}
							} else {
								continue;
							}
						}
					}
				}
			}
		} catch (SVNException e) {
			e.printStackTrace();
		}

		recursiveResearch(tmp, beginVersion - QUERY_VERSION_THRESHOLD, beginVersion);
	}

	public int getLatestVersion() {
		return (int) latest;
	}

}

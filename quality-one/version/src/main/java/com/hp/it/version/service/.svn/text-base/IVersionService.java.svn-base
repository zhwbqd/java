package com.hp.it.version.service;

import java.util.Map;
import java.util.Set;

import org.tmatesoft.svn.core.SVNException;

import com.hp.it.version.bean.LogEntry;

public interface IVersionService {
	
	public void initialize(String groupId, String artifactId, String urlStr, String userName, String password,
			boolean usingPrivateKey, String privateKeyFilePath, String passphrase) throws SVNException;

	public int getLatestVersion();

	public Map<String, LogEntry> findLatestLogEntriesByQualifiedClassNames(Set<String> nameSet);
}

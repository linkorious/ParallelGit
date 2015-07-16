package com.beijunyi.parallelgit.filesystem;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;

/**
 * General {@code GitFileSystem} manipulation utilities.
 */
public final class GitFileSystemUtils {

  public static void fastDeleteDirectory(@Nonnull Path path) throws IOException {
    GitPath gitPath = (GitPath) path;
    gitPath.getFileSystem().getFileStore().fastDeleteDirectory(gitPath.getNormalizedString());
  }

  @Nullable
  public static ObjectId writeTree(@Nonnull FileStore store) throws IOException {
    return ((GitFileStore) store).writeTree();
  }

  @Nullable
  public static ObjectId writeTree(@Nonnull FileSystem fs) throws IOException {
    return writeTree(((GitFileSystem) fs).getFileStore());
  }

  @Nullable
  public static ObjectId writeTree(@Nonnull Path path) throws IOException {
    return writeTree(((GitPath)path).getFileSystem());
  }

  @Nullable
  public static RevCommit commit(@Nonnull FileStore store, @Nonnull PersonIdent author, @Nonnull PersonIdent committer, @Nonnull String message, boolean amend) throws IOException {
    return ((GitFileStore) store).writeCommit(author, committer, message, amend);
  }

  @Nullable
  public static RevCommit commit(@Nonnull FileSystem fs, @Nonnull PersonIdent author, @Nonnull PersonIdent committer, @Nonnull String message, boolean amend) throws IOException {
    return commit(((GitFileSystem) fs).getFileStore(), author, committer, message, amend);
  }

  @Nullable
  public static RevCommit commit(@Nonnull Path path, @Nonnull PersonIdent author, @Nonnull PersonIdent committer, @Nonnull String message, boolean amend) throws IOException {
    return commit(((GitPath) path).getFileSystem(), author, committer, message, amend);
  }

  @Nullable
  public static RevCommit commit(@Nonnull Path path, @Nonnull PersonIdent author, @Nonnull String message, boolean amend) throws IOException {
    return commit(path, author, author, message, amend);
  }

  @Nullable
  public static RevCommit commit(@Nonnull Path path, @Nonnull String authorName, @Nonnull String authorEmail, @Nonnull String message, boolean amend) throws IOException {
    return commit(path, new PersonIdent(authorName, authorEmail), message, amend);
  }

  @Nullable
  public static RevCommit commit(@Nonnull Path path, @Nonnull String authorName, @Nonnull String authorEmail, @Nonnull String message) throws IOException {
    return commit(path, authorName, authorEmail, message, false);
  }

}
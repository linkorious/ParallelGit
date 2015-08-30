package com.beijunyi.parallelgit.utils;

import java.io.IOException;

import com.beijunyi.parallelgit.AbstractParallelGitTest;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.ObjectId;
import org.junit.Assert;
import org.junit.Test;

public class BlobHelperTest extends AbstractParallelGitTest {

  @Test
  public void findFileBlobIdTest() throws IOException {
    initRepository();
    String file = "a.txt";
    AnyObjectId fileBlob = writeToCache(file);
    AnyObjectId commit = commitToMaster();
    Assert.assertEquals(fileBlob, BlobHelper.findBlobId(repo, commit, file));
  }

  @Test
  public void findNonExistentFileBlobIdTest() throws IOException {
    AnyObjectId commit = initRepository();
    Assert.assertNull(BlobHelper.findBlobId(repo, commit, "non-existent.txt"));
  }

}
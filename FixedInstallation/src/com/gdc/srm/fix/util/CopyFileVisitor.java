package com.gdc.srm.fix.util;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyFileVisitor extends SimpleFileVisitor<Path> {
    private final Path targetPath;
    private Path sourcePath = null;
    public CopyFileVisitor(Path targetPath) {
        this.targetPath = targetPath;
    }

    public FileVisitResult preVisitDirectory(final Path dir,
    final BasicFileAttributes attrs) throws IOException {
        if (sourcePath == null) {
            sourcePath = dir;
        } else {
        	System.out.println("piando fdirectorio "+dir);
        Files.createDirectories(targetPath.resolve(sourcePath
                    .relativize(dir)));
        }
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult visitFile(final Path file,
    final BasicFileAttributes attrs) throws IOException {
    	System.out.println("copiando archivo"+file);
    Files.copy(file,
        targetPath.resolve(sourcePath.relativize(file)));
    return FileVisitResult.CONTINUE;
    }
}
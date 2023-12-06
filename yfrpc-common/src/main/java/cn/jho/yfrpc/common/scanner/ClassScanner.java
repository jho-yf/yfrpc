package cn.jho.yfrpc.common.scanner;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 通用类扫描器
 *
 * @author JHO xu-jihong@qq.com
 */
public class ClassScanner {

    /**
     * 文件：扫描当前工程下指定包下的所有类信息
     */
    private static final String PROTOCAL_FILE = "file";

    /**
     * Jar包：扫描jar文件中指定包下的所有类信息
     */
    private static final String PROTOCAL_JAR = "jar";

    /**
     * class文件后缀：扫描的过程中指定需要处理的文件的后缀信息
     */
    private static final String CLASS_FILE_SUFFIX = ".class";

    /**
     * 扫描当前工程中指定包下的所有类信息
     *
     * @param pkgName    扫描的包名
     * @param pkgPath    包路径
     * @param recursive  是否递归调用
     * @param classNames 类名列表
     */
    private static void findAndAddClassesInPkgByFile(String pkgName, String pkgPath,
            final boolean recursive, List<String> classNames) {
        File dir = new File(pkgPath);

        // 不存在或不是目录则跳过
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        // 获取子目录和以.class结尾的文件
        File[] files = dir.listFiles(
                file -> (recursive && file.isDirectory()) || file.getName().endsWith(CLASS_FILE_SUFFIX));
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                // 递归扫描子目录
                findAndAddClassesInPkgByFile(pkgName + "." + file.getName(), file.getAbsolutePath(), recursive,
                        classNames);
            } else {
                // 去掉class文件的后缀 ".class"
                String className = file.getName().substring(0, file.getName().length() - 6);
                classNames.add(className);
            }
        }

    }

    /**
     * 扫描jar包中指定包名下的所有类信息
     *
     * @param pkgName    扫描的包名
     * @param url        Jar包的url路径
     * @param pkgDirName 当前包名所属文件名
     * @param recursive  是否递归调用
     * @param classNames 类名列表
     * @throws IOException 读取jar包失败
     */
    private static String findAndAddClassesInPkgByJar(String pkgName, URL url, String pkgDirName,
            final boolean recursive,
            List<String> classNames) throws IOException {
        JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
        Enumeration<JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String name = entry.getName();

            // 如果以"/"开头，则去掉开头的"/"
            if (name.charAt(0) == '/') {
                name = name.substring(1);
            }

            // 不是在指定文件下，跳过
            if (!name.startsWith(pkgDirName)) {
                continue;
            }

            int idx = name.lastIndexOf('/');
            if (idx != -1) {
                // 以"/"为结尾，说明是个包
                pkgName = name.substring(0, idx).replace("/", ".");
            }

            // 递归获取ClassName
            if ((idx != -1 || recursive) && (name.endsWith(CLASS_FILE_SUFFIX) && !entry.isDirectory())) {
                // 去掉class文件的后缀 ".class"
                String className = name.substring(pkgName.length() + 1, name.length() - 6);
                classNames.add(className);
            }
        }

        return pkgName;
    }

}

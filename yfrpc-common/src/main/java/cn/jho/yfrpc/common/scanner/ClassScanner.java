package cn.jho.yfrpc.common.scanner;

import java.io.File;
import java.util.List;

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

}

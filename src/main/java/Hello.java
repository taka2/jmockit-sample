import java.io.File;
import java.io.FileFilter;


public class Hello {
	public static String helloStatic() {
		return "helloStatic()";
	}
	
	public String helloInstance1() {
		return "helloInstance1";
	}

	public String helloInstance2() {
		return "helloInstance2";
	}
	
	public File[] getFiles() {
		// このファイルフィルタは、このメソッドが呼ばれると動的にロードされる
		return new File(".").listFiles(new FileFilter() {
			public boolean accept(File file) {
				return true;
			}
		});
	}
}

package liferay.asm.tranformer;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.ACC_INTERFACE;
import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.ATHROW;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.NEW;

public class MethodTransformer {
	public static void main(String[] args) throws IOException {
		MethodTransformer methodTransformer = new MethodTransformer();

		methodTransformer.transform();
	}

	public void transform() throws IOException {
		InputStream in = testingMethod.class.getResourceAsStream("testingMethod.class");

		ClassReader classReader = new ClassReader(in);

		ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

		ExceptionThrower exceptionThrower = new ExceptionThrower(classWriter);

		classReader.accept(exceptionThrower, 0);

		File outputDir = new File("out/classes/methodtester/");

        outputDir.mkdirs();

        DataOutputStream dataOutputStream =
			new DataOutputStream(
				new FileOutputStream(
					new File(outputDir,"testingMethod.class")));

        dataOutputStream.write(classWriter.toByteArray());
	}

	public static class ExceptionThrower extends ClassVisitor {
		private boolean _isInterface;

		public ExceptionThrower(ClassVisitor classVisitor) {
			super(ASM4, classVisitor);
		}

		@Override
		public void visit(
			int version, int access, String name, String signature,
			String superName, String[] interfaces) {

			cv.visit(version, access, name, signature, superName, interfaces);

			_isInterface = (access == ACC_INTERFACE);
		}

		@Override
		public MethodVisitor visitMethod(
			int access, String name, String desc, String signature,
			String[] exceptions) {

			MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
				exceptions);

			if (!_isInterface && mv != null && !name.equals("<init>") &&
				!name.startsWith("set")) {

				ExceptionThrowerMethod exceptionThrowerMethod =
					new ExceptionThrowerMethod(mv);

				return exceptionThrowerMethod;
			}

			return mv;
		}

		public static class ExceptionThrowerMethod extends MethodVisitor {
			private final MethodVisitor _methodMethodVisitor;

			public ExceptionThrowerMethod(MethodVisitor methodVisitor) {
				super(ASM4, null);
				_methodMethodVisitor = methodVisitor;
			}

			@Override
			public void visitCode() {
				_methodMethodVisitor.visitCode();
				_methodMethodVisitor.visitTypeInsn(
					NEW, "java/lang/UnsupportedOperationException");
				_methodMethodVisitor.visitInsn(DUP);
				_methodMethodVisitor.visitMethodInsn(
					INVOKESPECIAL, "java/lang/UnsupportedOperationException",
					"<init>", "()V", false);
				_methodMethodVisitor.visitInsn(ATHROW);
				_methodMethodVisitor.visitMaxs(2, 0);
				_methodMethodVisitor.visitEnd();

//				_methodMethodVisitor.visitCode();
//				_methodMethodVisitor.visitTypeInsn(NEW, "java/io/IOException");
//				_methodMethodVisitor.visitInsn(DUP);
//				_methodMethodVisitor.visitMethodInsn(INVOKESPECIAL, "java/io/IOException", "<init>", "()V", false);
//				_methodMethodVisitor.visitInsn(ATHROW);
//				_methodMethodVisitor.visitMaxs(2, 0);
//				_methodMethodVisitor.visitEnd();
			}
		}
	}
}

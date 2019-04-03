package IOtask;

public class IOrunner {

    public static void main(String[] args) {

        ReadWriteReflection readWriteReflection = new ReadWriteReflection(
                "/Users/wasel/IdeaProjects/untitled/src/IOtask/input.txt",
                "/Users/wasel/IdeaProjects/untitled/src/IOtask/outputResult.txt");
        readWriteReflection.go();

    }

}

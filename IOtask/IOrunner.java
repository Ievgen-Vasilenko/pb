package IOtask;


import java.io.IOException;

public class IOrunner {

    public static void main(String[] args) throws IOException {

        ReadWriteReflection readWriteReflection = new ReadWriteReflection(
                "/Users/wasel/IdeaProjects/untitled/src/IOtask/input.txt",
                "/Users/wasel/IdeaProjects/untitled/src/IOtask/output.txt");
        readWriteReflection.go();

    }

}

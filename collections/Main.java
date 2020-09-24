package collections;

public class Main {

    public static void main(String[] args) {
        StackOfStrings stackLL = new LinkedStackOfStrings();
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if (s.equals("-"))
                System.out.print(stackLL.pop() + " ");
            else
                stackLL.push(s);
        }
        System.out.println();
        StackOfStrings stackFA = new FixedCapacityStackOfStrings(10);
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if (s.equals("-"))
                System.out.print(stackFA.pop() + " ");
            else {
                stackFA.push(s);
            }
        }
        System.out.println();
        StackOfStrings stackRA = new ResizingArrayStackOfString();
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if (s.equals("-"))
                System.out.print(stackRA.pop() + " ");
            else {
                stackRA.push(s);
            }
        }
        System.out.println();
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if (s.equals("-"))
                System.out.print(stack.pop() + " ");
            else {
                stack.push(s);
            }
        }
        System.out.println();
        FixedCapacityStack<String> stackFC = new FixedCapacityStack<String>(10);
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if (s.equals("-"))
                System.out.print(stackFC.pop() + " ");
            else {
                stackFC.push(s);
            }
        }
        System.out.println();
        ResizingArrayStack<String> stackUFC = new ResizingArrayStack<String>();
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if (s.equals("-"))
                System.out.print(stackUFC.pop() + " ");
            else {
                stackUFC.push(s);
            }
        }
        
        System.out.println();
        for (String s: stackUFC) {
            System.out.print(s);
        }

    }
}

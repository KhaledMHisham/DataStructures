package test.stack;

import java.util.ArrayList;
import main.stack.ArrayStack;
import java.util.List;

import main.stack.IntStack;
import main.stack.ListStack;
import main.stack.Stack;
import org.junit.Before;
import org.junit.Test;



public class StackTest {

    private List<Stack<Integer>> stacks = new ArrayList<>();

    @Before
    public void setup() {
        stacks.add(new ListStack<Integer>());
        stacks.add(new ArrayStack<Integer>());
        stacks.add(new IntStack());
    }

    @Test
    public void testEmptyStack() {
        for (Stack stack : stacks) {
            assert(stack.isEmpty());
            assert(stack.size() == 0);
        }
    }

    @Test(expected = Exception.class)
    public void testPopOnEmpty() {
        for (Stack stack : stacks) {
            stack.pop();
        }
    }

    @Test(expected = Exception.class)
    public void testPeekOnEmpty() {
        for (Stack stack : stacks) {
            stack.peek();
        }
    }

    @Test
    public void testPush() {
        for (Stack<Integer> stack : stacks) {
            stack.push(2);
            assert(stack.size() == 1);
        }
    }

    @Test
    public void testPeek() {
        for (Stack<Integer> stack : stacks) {
            stack.push(2);
            assert(stack.peek() == 2);
            assert(stack.size() == 1);
        }
    }

    @Test
    public void testPop() {
        for (Stack<Integer> stack : stacks) {
            stack.push(2);
            assert(stack.pop() == 2);
            assert(stack.size() == 0);
        }
    }

    @Test
    public void testExhaustively() {
        for (Stack<Integer> stack : stacks) {
            assert(stack.isEmpty());
            stack.push(1);
            assert(!stack.isEmpty());
            stack.push(2);
            assert(stack.size() == 2);
            assert(stack.peek() == 2);
            assert(stack.size() == 2);
            assert(stack.pop() == 2);
            assert(stack.size() == 1);
            assert( stack.peek() == 1);
            assert(stack.size() == 1);
            assert( stack.pop() == 1);
            assert(stack.size() == 0);
            assert(stack.isEmpty());
        }
    }
}

package week1.queuesStacks;

import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

public class HtmlValidator {
	
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		Stack<HtmlTag> stack = new Stack<HtmlTag>();

		for (HtmlTag tag: tags) {
			if (tag.isOpenTag()) {
				stack.push(tag);
			} else if (tag.isSelfClosing()) {
				// Nothing, continues to scroll
			} else {
				if (stack.isEmpty()) {
					return null;
				} else if (tag.matches(stack.peek())) {
					stack.pop();
				} else {
					return stack;
				}
			}
		}
		
		return stack;
	}
	

}


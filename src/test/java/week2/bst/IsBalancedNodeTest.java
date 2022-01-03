package week2.bst;

import week2.bst.BinarySearchTree.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IsBalancedNodeTest {
    protected BinarySearchTree<String> tree;

    public IsBalancedNodeTest() {
    }

    @Before
    public void setUp() throws Exception {
        this.tree = new BinarySearchTree();
        this.tree.add("dog");
        this.tree.add("cat");
        this.tree.add("pig");
    }

    @Test
    public void testNull() {
        try {
            boolean var1 = this.tree.isBalanced((Node)null);
            Assert.assertFalse("isBalanced should be false when input value is null", var1);
        } catch (Exception var2) {
            Assert.fail("isBalanced throws " + var2 + " when input value is null");
        }

    }

    @Test
    public void testNotInTreeDifferentValue() {
        /*
        BinarySearchTree var10002 = this.tree;
        var10002.getClass();
        Node var1 = new Node(var10002, "banana");
        */

        // Modified version because the first one doesn't work
        BinarySearchTree<String> var10002 = new BinarySearchTree<>();
        var10002.add("banana");
        BinarySearchTree<String>.Node var1 = var10002.findNode("banana");

        try {
            boolean var2 = this.tree.isBalanced(var1);
            Assert.assertFalse("isBalanced should be false when input Node is not in tree", var2);
        } catch (Exception var3) {
            Assert.fail("isBalanced throws " + var3 + " when input Node is not in tree");
        }

    }

    @Test
    public void testRootBalanced() {
        BinarySearchTree<String>.Node var1 = this.tree.findNode("dog");

        try {
            boolean var2 = this.tree.isBalanced(var1);
            Assert.assertTrue("isBalanced should be true when input Node is root of tree with two children", var2);
        } catch (Exception var3) {
            Assert.fail("isBalanced throws " + var3 + " when input Node is root of tree with two children");
        }

    }

    @Test
    public void testLeaf() {
        BinarySearchTree<String>.Node var1 = this.tree.findNode("cat");

        try {
            boolean var2 = this.tree.isBalanced(var1);
            Assert.assertTrue("isBalanced should be true when input Node is leaf", var2);
        } catch (Exception var3) {
            Assert.fail("isBalanced throws " + var3 + " when input Node is leaf");
        }

    }

    @Test
    public void testLeftHeightIsOneGreaterThanRight() {
        this.tree.add("ant");
        BinarySearchTree<String>.Node var1 = this.tree.findNode("dog");

        try {
            boolean var2 = this.tree.isBalanced(var1);
            Assert.assertTrue("isBalanced should be true when difference in heights of child nodes is 1", var2);
        } catch (Exception var3) {
            Assert.fail("isBalanced throws " + var3 + " when difference in heights of child nodes is 1");
        }

    }

    @Test
    public void testRightHeightIsOneGreaterThanLeft() {
        this.tree.add("zebra");
        BinarySearchTree<String>.Node var1 = this.tree.findNode("dog");

        try {
            boolean var2 = this.tree.isBalanced(var1);
            Assert.assertTrue("isBalanced should be true when difference in heights of child nodes is 1", var2);
        } catch (Exception var3) {
            Assert.fail("isBalanced throws " + var3 + " when difference in heights of child nodes is 1");
        }

    }

    @Test
    public void testLeftHeightIsTwoGreaterThanRight() {
        this.tree.add("ant");
        this.tree.add("aah!");
        BinarySearchTree<String>.Node var1 = this.tree.findNode("dog");

        try {
            boolean var2 = this.tree.isBalanced(var1);
            Assert.assertFalse("isBalanced should be false when difference in heights of child nodes is more than 1", var2);
        } catch (Exception var3) {
            Assert.fail("isBalanced throws " + var3 + " when difference in heights of child nodes is more than 1");
        }

    }

    @Test
    public void testRightHeightIsTwoGreaterThanLeft() {
        this.tree.add("rat");
        this.tree.add("skunk");
        BinarySearchTree<String>.Node var1 = this.tree.findNode("dog");

        try {
            boolean var2 = this.tree.isBalanced(var1);
            Assert.assertFalse("isBalanced should be false when difference in heights of child nodes is more than 1", var2);
        } catch (Exception var3) {
            Assert.fail("isBalanced throws " + var3 + " when difference in heights of child nodes is more than 1");
        }

    }
}

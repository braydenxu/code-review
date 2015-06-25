<?php
/**
 * Lei Xu code sample for interview code review section -- PHP version
 *
 * This code is a simplified version of two functions of the tree-like file manager
 * modifyNode() is to modify (update, delete) any node (folder or file) metadata to a new value
 * findPath() is to find all the paths from root to leaf (getting all the absolute paths of any specified file)
 *
 *
 */


class TreeNode {
    var $left;
    var $right;
    var $val;
    function TreeNode($val) {
        $this->val = $val;
    }
}

// build tree
/**
 *         1
 *      2    3
 *    4  5     4
 */
$root = new TreeNode(1);
$root->left = new TreeNode(2);
$root->right = new TreeNode(3);
$root->left->left = new TreeNode(4);
$root->left->right = new TreeNode(5);
$root->right->right = new TreeNode(4);

//modifyNode() is to modify (update, delete) any node (folder or file) metadata to a new value
function modifyNode($root, $target, $newValue = null) {
    if ($root == null) {
        return null;
    }

    if ($root->val == $target) {
        if ($newValue != null) {
            $root->val = $newValue;
        }
        return $root;
    }

    $left = modifyNode($root->left, $target, $newValue);
    $right = modifyNode($root->right, $target, $newValue);

    return $left != null ? $left : $right;
}

// test case for modifyNode
$res = modifyNode($root, 5, 12);
echo "modifyNode() result:\n";
echo $res->val;
echo "\n\n";

//findPath() is to find all the paths from root to leaf (getting all the absolute paths of any specified file)
function findPath($root, $target) {
    $result = array();
    $path = array();
    helper($root, $target, $path, $result);
    return $result;
}

function helper($root, $target, &$path, &$result) {
    if ($root == null) {
        return;
    }

    if ($root->left == null && $root->right == null) {
        if ($root->val == $target) {
            $path[] = $root->val;
            $result[] = $path;
            array_pop($path);
        }
        return;
    }

    $path[] = $root->val;
    helper($root->left, $target, $path, $result);
    helper($root->right, $target, $path, $result);
    array_pop($path);
}

// test case for findPath
$result = findPath($root, 4);
echo "findPath() result:\n";
foreach ($result as $path) {
    foreach ($path as $node) {
        echo $node;
    }
    echo "\n";
}






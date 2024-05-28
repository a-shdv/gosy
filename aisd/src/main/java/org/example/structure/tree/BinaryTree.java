package org.example.structure.tree;

// Бинарное дерево


// Класс бинарного дерева
public class BinaryTree {
    // Класс узла дерева
    private static class TreeNode {
        int val;
        TreeNode left, right;

        // Конструктор
        public TreeNode(int val) {
            this.val = val;
            left = right = null;
        }
    }

    TreeNode root;

    // Конструктор
    public BinaryTree() {
        root = null;
    }

    // Метод добавления значения в дерево
    public void insert(int val) {
        root = insertRec(root, val);
    }

    // Рекурсивная вспомогательная функция для вставки значения в дерево
    private TreeNode insertRec(TreeNode root, int val) {
        // Если дерево пустое, создаем новый узел
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }

        // Рекурсивно спускаемся по дереву
        if (val < root.val)
            root.left = insertRec(root.left, val);
        else if (val > root.val)
            root.right = insertRec(root.right, val);

        return root;
    }

    // Метод для проверки наличия значения в дереве
    public boolean search(int val) {
        return searchRec(root, val);
    }

    // Рекурсивная вспомогательная функция для поиска значения в дереве
    private boolean searchRec(TreeNode root, int val) {
        // Если дерево пустое или достигнут конец ветви, возвращаем false
        if (root == null)
            return false;

        // Если значение найдено, возвращаем true
        if (root.val == val)
            return true;

        // Рекурсивно ищем в левом и правом поддеревьях
        return val < root.val ? searchRec(root.left, val) : searchRec(root.right, val);
    }

    // Метод удаления значения из дерева
    public void delete(int val) {
        root = deleteRec(root, val);
    }

    // Рекурсивная вспомогательная функция для удаления значения из дерева
    private TreeNode deleteRec(TreeNode root, int val) {
        // Если дерево пустое, возвращаем null
        if (root == null)
            return root;

        // Рекурсивно спускаемся по дереву
        if (val < root.val)
            root.left = deleteRec(root.left, val);
        else if (val > root.val)
            root.right = deleteRec(root.right, val);
        else {
            // Узел с одним или без детей
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Узел с двумя детьми: находим наименьший элемент в правом поддереве
            root.val = minValue(root.right);

            // Удаляем наименьший элемент из правого поддерева
            root.right = deleteRec(root.right, root.val);
        }

        return root;
    }

    // Вспомогательная функция для поиска наименьшего элемента в дереве
    private int minValue(TreeNode root) {
        int minv = root.val;
        while (root.left != null) {
            minv = root.left.val;
            root = root.left;
        }
        return minv;
    }
}



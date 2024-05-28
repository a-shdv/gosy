package org.example.util.sort.gpt;

public class TournamentSortUtils {
    public static void sort(int[] array) {
        int n = array.length;
        int[] tree = new int[2 * n - 1];
        buildTree(array, tree, n);
        for (int i = 0; i < n; i++) {
            array[i] = extractMin(tree, n + i);
        }
    }

    private static void buildTree(int[] array, int[] tree, int n) {
        for (int i = 0; i < n; i++) {
            tree[n + i] = i;
        }
        for (int i = n - 1; i > 0; i--) {
            tree[i] = array[tree[2 * i]] < array[tree[2 * i + 1]] ? tree[2 * i] : tree[2 * i + 1];
        }
    }

    private static int extractMin(int[] tree, int root) {
        int result = tree[root];
        while (root > 0) {
            if (result == tree[root]) {
                root--;
            } else {
                result = tree[root];
                break;
            }
        }
        return result;
    }
}
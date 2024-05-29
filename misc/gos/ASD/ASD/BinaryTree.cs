using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace ASD;
public class BinaryTree
{
    private Node? root;
    public void Add(int value)
    {
        if (root == null)
        {
            root = new Node { Data = value };
        }
        else
        {
            AddTo(root, value);
        }
    }

    private void AddTo(Node node, int value)
    {
        //Случай 1: Вставляемое значение меньше значения узла
        if (value < node.Data)
        {
            //Если нет левого поддерева, добавляем значение в левого ребенка,
            if (node.Left == null)
            {
                node.Left = new Node { Data = value };
            }
            else
            {
                //в противном случае повторяем для левого поддерева.
                AddTo(node.Left, value);
            }
        }
        //Случай 2: Вставляемое значение больше или равно значению узла.
        else
        {
            //Если нет правого поддерева, добавляем значение в правого ребенка,
            if (node.Right == null)
            {
                node.Right = new Node { Data = value };
            }
            else
            {
                //в противном случае повторяем для правого поддерева.
                AddTo(node.Right, value);
            }
        }
    }

    public void Remove(int value)
    {
        Node current;
        Node parent;

        // Находим удаляемый узел.
        current = FindWithParent(value, out parent);

        if (current == null)
        {
            return;
        }

        //Случай 1: Если нет детей справа,
        //левый ребенок встает на место удаляемого.
        if (current.Right == null)
        {
            if (parent == null)
            {
                root = current.Left;
            }
            else
            {
                if (parent.Data > current.Data)
                {
                    //Если значение родителя больше текущего,
                    //левый ребенок текущего узла становится левым ребенком родителя.
                    parent.Left = current.Left;
                }
                if (parent.Data < current.Data)
                {
                    //Если значение родителя меньше текущего,
                    // левый ребенок текущего узла становится правым ребенком родителя.
                    parent.Right = current.Left;
                }
            }
        }
        //Случай 2: Если у правого ребенка нет детей слева
        //то он занимает место удаляемого узла.
        else if (current.Right.Left == null)
        {
            current.Right.Left = current.Left;
            if (parent == null)
            {
                root = current.Right;
            }
            else
            {
                if (parent.Data > current.Data)
                {
                    //Если значение родителя больше текущего,
                    //правый ребенок текущего узла становится левым ребенком родителя.
                    parent.Left = current.Right;
                }
                else if (parent.Data < current.Data)
                {
                    //Если значение родителя меньше текущего,
                    // правый ребенок текущего узла становится правым ребенком родителя.
                    parent.Right = current.Right;
                }
            }
        }
        //Случай 3: Если у правого ребенка есть дети слева,
        //крайний левый ребенок
        //из правого поддерева заменяет удаляемый узел.
        else
        {
            //Найдем крайний левый узел.
            Node leftmost = current.Right.Left;
            Node leftmostParent = current.Right;
            while (leftmost.Left != null)
            {
                leftmostParent = leftmost;
                leftmost = leftmost.Left;
            }
            //Левое поддерево родителя становится правым поддеревом
            //крайнего левого узла.
            leftmostParent.Left = leftmost.Right;
            //Левый и правый ребенок текущего узла становится левым
            //и правым ребенком крайнего левого.
            leftmost.Left = current.Left;
            leftmost.Right = current.Right;
            if (parent == null)
            {
                root = leftmost;
            }
            else
            {
                if (parent.Data > current.Data)
                {
                    //Если значение родителя больше текущего,
                    //крайний левый узел становится левым ребенком родителя.
                    parent.Left = leftmost;
                }
                else if (parent.Data < current.Data)
                {
                    //Если значение родителя меньше текущего,
                    //крайний левый узел становится правым ребенком родителя.
                    parent.Right = leftmost;
                }
            }
        }
    }

    private Node? FindWithParent(int value, out Node? parent)
    {
        //Попробуем найти значение в дереве.
        Node? current = root;
        parent = null;

        //До тех пор, пока не нашли...
        while (current != null)
        {
            if (current.Data > value)
            {
                //Если искомое значение меньше, идем налево.
                parent = current;
                current = current.Left;
            }
            else if (current.Data < value)
            {
                //Если искомое значение больше, идем направо.
                parent = current;
                current = current.Right;
            }
            else if(current.Data == value)
            {
                //Если равны, то останавливаемся
                break;
            }
        }

        return current;
    }

    public bool Contains(int value)
    {
        Node parent;
        return FindWithParent(value, out parent) != null;
    }
}

public class Node
{
    public int Data;
    public Node? Left;
    public Node? Right;
}

namespace ASD;
public class DoubleLinkedList
{
    private DoubleLinkedListNode? root;

    public void Add(int value)
    {
        if (root == null)
        {
            root = new DoubleLinkedListNode { Data = value };
        }
        else
        {
            var item = root;
            while (item != null)
            {
                if (item.Next == null)
                {
                    item.Next = new DoubleLinkedListNode { Data = value, Prev = item };
                    return;
                }
                item = item.Next;
            }
        }
    }

    public void Print()
    {
        Console.WriteLine("Элементы списка:");
        if (root == null)
        {
            Console.WriteLine("Список пуст!");
        }
        else
        {
            var item = root;
            while (item != null)
            {
                Console.Write(item.Data + " ");
                item = item.Next;
            }
            Console.WriteLine();
        }
    }

    public void Remove(int value)
    {
        if (root == null)
        {
            return;
        }
        else
        {
            if (root.Data == value)
            {
                if (root.Next == null)
                {
                    root = null;
                }
                else
                {
                    root.Next.Prev = null;
                    root = root.Next;
                }

                return;
            }

            var item = root;
            while (item.Next != null)
            {
                if (item.Next.Data == value)
                {
                    if (item.Next.Next != null)
                    {
                        item.Next.Next.Prev = item;
                    }
                    item.Next = item.Next.Next;
                    return;
                }
                item = item.Next;
            }
        }
    }

    public void Clear()
    {
        root = null;
    }
}

public class DoubleLinkedListNode
{
    public int Data { get; set; }

    public DoubleLinkedListNode? Next { get; set; }
    public DoubleLinkedListNode? Prev { get; set; }
}

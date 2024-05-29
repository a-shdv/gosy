namespace ASD;
public class SingleLinkedList
{
    private SingleLinkedListNode? root;

    public void Add(int value)
    {
        if (root == null)
        {
            root = new SingleLinkedListNode { Data = value };
        }
        else
        {
            var item = root;
            while(item != null)
            {
                if(item.Next == null)
                {
                    item.Next = new SingleLinkedListNode { Data = value };
                    return;
                }
                item = item.Next;
            }
        }
    }

    public void Print()
    {
        Console.WriteLine("Элементы списка:");
        if(root == null)
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
        if(root == null)
        {
            return;
        }
        else
        {
            if(root.Data == value)
            {
                if(root.Next == null)
                {
                    root = null;
                }
                else
                {
                    root = root.Next;
                }

                return;
            }

            var item = root;
            while (item.Next != null)
            {
                if(item.Next.Data == value)
                {
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

public class SingleLinkedListNode
{
    public int Data { get; set; }

    public SingleLinkedListNode? Next { get; set; }
}

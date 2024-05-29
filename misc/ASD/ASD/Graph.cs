namespace ASD;

public class Graph
{
    public List<Vertex> Vertex { get; set; } = new List<Vertex>();

    public void AddVertex()
    {
        Vertex.Add(new Vertex { Id = Vertex.Count });
    }

    public Vertex? GetVertex(int id)
    {
        return Vertex.FirstOrDefault(x => x.Id == id);
    }

    public void AddEdge(int id1, int id2)
    {
        var vertex1 = GetVertex(id1);
        var vertex2 = GetVertex(id2);
        if (vertex1 != null && vertex2 != null)
        {
            vertex1.AddEdge(new Edge { Vertex = vertex2 });
            vertex2.AddEdge(new Edge { Vertex = vertex1 });
        }
    }

    public void BFS(int id)
    {
        var array = new int[Vertex.Count];
        for (int i = 0; i < array.Length; i++)
        {
            array[i] = -1;
        }
        var queue = new Queue<Vertex>();

        queue.Enqueue(Vertex.First(x => x.Id == id));

        int iteration = 1;
        while (queue.Count != 0)
        {
            var vertex = queue.Dequeue();

            Console.WriteLine($"Итерация {iteration}: Алгоритм зашел в {vertex.Id} вершину");
            array[vertex.Id] = iteration;

            var edges = vertex.Edges;
            foreach (var edge in edges)
            {
                if (array[edge.Vertex.Id] == -1 && !queue.Contains(GetVertex(edge.Vertex.Id)))
                {
                    Console.WriteLine($"Алгоритм добавил в очередь {edge.Vertex.Id} вершину");
                    queue.Enqueue(edge.Vertex);
                }
            }
            iteration++;
        }

        Console.WriteLine($"Алгоритм прошел все вершины");
    }
}

public class Vertex
{
    public int Id { get; set; }

    public List<Edge> Edges { get; set; } = new List<Edge>();

    public void AddEdge(Edge edge)
    {
        Edges.Add(edge);
    }

    public override string ToString()
    {
        return $"Элемент {Id}";
    }
}

public class Edge
{
    public Vertex Vertex { get; set; }

}
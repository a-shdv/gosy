using System.Runtime.CompilerServices;
using System.Xml.Serialization;

namespace TP_Forms;

public partial class Form1 : Form
{
    private readonly Random random = new Random();
    private readonly XmlSerializer serializer = new XmlSerializer(typeof(List<Person>));
    private List<Person> persons = new List<Person>();
    private List<Result> results = new List<Result>();
    private const string filePath = "C:\\Users\\a-shdv\\Desktop\\res.xml";

    public Form1()
    {
        InitializeComponent();
    }

    private void PrintList()
    {
        richTextBox.Clear();
        foreach (var person in persons)
        {
            richTextBox.Text += $"ID: {person.Id}, Name: {person.Name}, Age: {person.Age}, Count of payments: {person.Payments.Count}\n";
        }
    }

    private void buttonCreate_Click(object sender, EventArgs e)
    {
        var list = new List<Person>();
        for (int i = 0; i < 10; i++)
        {
            list.Add(new Person() { Id = random.Next(1, 3), Age = random.Next(20, 30), Name = Guid.NewGuid().ToString(), Payments = new List<int> { random.Next(), random.Next() } });
        }
        persons = list;
        PrintList();
    }

    private void buttonClear_Click(object sender, EventArgs e)
    {
        persons = new List<Person>();
        PrintList();
    }

    private void buttonSave_Click(object sender, EventArgs e)
    {
        using (var stream = new FileStream(filePath, FileMode.OpenOrCreate))
        {
            serializer.Serialize(stream, persons);
        }
    }

    private void buttonLoad_Click(object sender, EventArgs e)
    {
        using (var stream = new FileStream(filePath, FileMode.Open))
        {
            persons = (List<Person>)serializer.Deserialize(stream);
        }
        PrintList();
    }

    private void PrintChangedList()
    {
        richTextBox.Clear();
        foreach (var result in results)
        {
            richTextBox.Text += $"ID: {result.Id}, Count: {result.Count}\n";
        }
    }

    private void buttonSelect_Click(object sender, EventArgs e)
    {

        results = persons.Where(x => x.Age > 25).GroupBy(x => x.Id).Select(x => new Result { Id = x.Key, Count = x.Count() }).ToList();
        PrintChangedList();
    }
}

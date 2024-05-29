using System.Xml.Serialization;

namespace TP_Forms;

[XmlRoot]
public class Person
{
    [XmlAttribute("id")]
    public int Id { get; set; }

    [XmlElement("name")]
    public string Name { get; set; }

    [XmlElement("age")]
    public int Age { get; set; }

    [XmlArray("payments")]
    public List<int> Payments { get; set; }
}

using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Text.Json.Serialization;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace Patient.Entity;

[XmlRoot]
public class Patient
{
    [XmlAttribute("id")]
    public int Id { get; set; }

    [XmlAttribute("name")]
    public string Name { get; set; }

    [XmlAttribute("age")]
    public int Age { get; set; }

    // foreign key prop
    [XmlAttribute("room_id")]
    public int RoomId { get; set; } 

    // navigation prop
    [ForeignKey("RoomId")]
    public Room Room { get; set; }
}


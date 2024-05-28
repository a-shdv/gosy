using Patient.Entity;
using System;
using System.Text.Json;
using System.Windows.Forms;
using System.Xml.Serialization;

namespace Patient
{
    public partial class FormMain : Form
    {
        private readonly Random random = new Random();
        private List<Dto.RichTextBoxDto> richTextBoxDtos = new List<Dto.RichTextBoxDto>();
        private List<Entity.Patient> patientsToSerializeXml = new List<Entity.Patient>();
        private List<Entity.Patient> patientsToDeserializeXml = new List<Entity.Patient>();
        private readonly XmlSerializer xmlSerializer = new XmlSerializer(typeof(List<Entity.Patient>));
        private const string xmlFilePath = "C:\\Users\\a-shdv\\Desktop\\res.xml";
        private int patientIdCounter = 1; // ������� ��� ID ���������
        private int roomIdCounter = 1; // ������� ��� ID ���������

        List<Entity.Room> rooms = new List<Entity.Room>();

        public FormMain()
        {
            InitializeComponent();
        }

        private void buttonAdd_Click(object sender, EventArgs e)
        {
            // �������� ���������� ����� ���������� ��������
            FormAddPatient addPatientForm = new FormAddPatient();

            // ����������� ����� ��� ���������� �������
            DialogResult result = addPatientForm.ShowDialog();

            // �������� ���������� �������
            if (result == DialogResult.OK)
            {
                // ��������� ������ � ����� �������� �� �����
                string name = addPatientForm.Name;
                int age = addPatientForm.Age;
                int roomId = addPatientForm.RoomId;

                // �������� ������ �������� � ���������� ��� � ������
                richTextBoxDtos.Add(new Dto.RichTextBoxDto
                {
                    Id = patientIdCounter++,
                    Name = name,
                    Age = age,
                    RoomId = roomId
                }); ;

                // ���������� RichTextBox
                UpdateRichTextBox();
            }
            else
            {
                // ��������, ���� ������ ��� ������� ��� ������ ��� ���������� ������
            }
        }

        private void buttonFill_Click(object sender, EventArgs e)
        {
            List<Entity.Patient> patients = new List<Entity.Patient>();
            InitPatients(patients, 5);
            InitRooms(rooms, patients, 5);

            foreach (var patient in patients)
            {
                richTextBoxDtos.Add(new Dto.RichTextBoxDto
                {
                    Id = patient.Id,
                    Name = patient.Name,
                    Age = patient.Age,
                    RoomId = patient.RoomId
                });
            }
            UpdateRichTextBox();
        }

        private void buttonSaveToXml_Click(object sender, EventArgs e)
        {
            if (File.Exists(xmlFilePath))
            {
                File.Delete(xmlFilePath);
            }
                patientsToSerializeXml.Clear();
            foreach (var dto in richTextBoxDtos)
            {
                patientsToSerializeXml.Add(new Entity.Patient
                {
                    Id = dto.Id,
                    Name = dto.Name,
                    Age = dto.Age,
                    RoomId = dto.RoomId
                });
            }

            // XML
            using (var xmlStream = new FileStream(xmlFilePath, FileMode.OpenOrCreate))
            {
                xmlSerializer.Serialize(xmlStream, patientsToSerializeXml);
            }
            MessageBox.Show("������ ������� ��������� � ������� XML");
        }

        private void buttonLoadFromXml_Click(object sender, EventArgs e)
        {
            if (File.Exists(xmlFilePath))
            {
                richTextBoxDtos.Clear();
                UpdateRichTextBox();
                using (var stream = new FileStream(xmlFilePath, FileMode.Open))
                {
                    try
                    {
                        patientsToDeserializeXml = (List<Entity.Patient>)xmlSerializer.Deserialize(stream);
                    }
                    catch (InvalidOperationException ex)
                    {
                        MessageBox.Show("�� ������� ��������� XML-����. ����������, ����������, �������� ������.");
                    }
                }
                foreach (var patient in patientsToDeserializeXml)
                {
                    richTextBoxDtos.Add(new Dto.RichTextBoxDto
                    {
                        Id = patient.Id,
                        Name = patient.Name,
                        Age = patient.Age,
                        RoomId = patient.RoomId
                    });
                }
            }
            else
            {
                MessageBox.Show("XML-���� �� ������");

            }
            UpdateRichTextBox();
        }

        private void buttonFindLinq_Click(object sender, EventArgs e)
        {
            // ����������� ����������� ���� ��� ����� ��������
            string input = Microsoft.VisualBasic.Interaction.InputBox("������� �������:", "���� ��������", "");

            // ��������, ���� �� ������� ��������
            if (!string.IsNullOrEmpty(input))
            {
                // ������� ������������� ��������� �������� � �����
                if (int.TryParse(input, out int age))
                {
                    // ���������� ��������� �� ���������� ��������
                    var olderPatients = richTextBoxDtos.Where(patient => patient.Age > age).ToList();

                    // ���������� ������ � RichTextBox
                    richTextBoxDtos = olderPatients;
                    UpdateRichTextBox();
                }
                else
                {
                    MessageBox.Show("������������ ����. ����������, ������� �������� ��������.");
                }
            }
        }

        private void buttonFindLambda_Click(object sender, EventArgs e)
        {
            richTextBoxDtos = richTextBoxDtos
                                .GroupBy(patient => patient.RoomId)
                                .OrderBy(room => room.Key) // ������������� �� RoomId �� �����������
                                .SelectMany(elements => elements.ToList()) // ����������� ������������������ ��������� � ���� ������������������
                                .ToList();

            var roomWithMostPatients = richTextBoxDtos
                                        .GroupBy(patient => patient.RoomId)
                                        .Select(group => new { RoomId = group.Key, Count = group.Count() })
                                        .OrderByDescending(x => x.Count).FirstOrDefault();
            if (roomWithMostPatients != null)
            {
                MessageBox.Show($"������� {roomWithMostPatients.RoomId} ����� ���������� ���������� ���������: {roomWithMostPatients.Count}");
            }
            UpdateRichTextBox();
        }

        private void buttonClear_Click(object sender, EventArgs e)
        {
            if (File.Exists(xmlFilePath))
            {
                File.Delete(xmlFilePath);
            }
            patientsToSerializeXml.Clear();
            richTextBoxDtos.Clear();
            richTextBox.Clear();
        }

        private void UpdateRichTextBox()
        {
            richTextBox.Clear();
            foreach (var dto in richTextBoxDtos)
            {
                richTextBox.Text += $" Id: {dto.Id}, Name: {dto.Name}, Age: {dto.Age}, Room: {dto.RoomId}\n";

            }
            //    richTextBox.Text += $"ID: {person.Id}, Name: {person.Name}, Age: {person.Age}, Count of payments: {person.Payments.Count}\n";

        }

        private void InitPatients(List<Entity.Patient> patients, int size)
        {
            for (int i = 0; i < size; i++)
            {
                patients.Add(new Entity.Patient()
                {
                    Id = patientIdCounter++,
                    Name = Guid.NewGuid().ToString(),
                    Age = random.Next(20, 30),
                });
            }
        }

        private void InitRooms(List<Entity.Room> rooms, List<Entity.Patient> patients, int numberOfRooms)
        {
            // ���� ������ ������ ����, �������� ��������� �������
            if (rooms.Count == 0)
            {
                for (int i = 0; i < numberOfRooms; i++)
                {
                    rooms.Add(new Entity.Room()
                    {
                        Id = roomIdCounter++,
                        Patients = new List<Entity.Patient>()
                    });
                }
            }

            // ����������� ������ ���������
            patients = patients.OrderBy(p => random.Next()).ToList();

            // ���������� ������������ ��������� �� ������������ ��������
            foreach (var patient in patients)
            {
                int roomIndex = (int)random.NextInt64(1, numberOfRooms);

                // �������� �������� � ������� �������
                rooms[roomIndex].Patients.Add(patient);
                // ��������� �������� � ������� �������
                patient.RoomId = rooms[roomIndex].Id;

                // ��������� � ��������� ������� ��� ��������� � ������, ���� ��������� ����� ������ ������
                roomIndex = (roomIndex + 1) % rooms.Count;
            }
        }
    }
}

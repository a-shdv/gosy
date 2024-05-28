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
        private int patientIdCounter = 1; // Счётчик для ID пациентов
        private int roomIdCounter = 1; // Счётчик для ID пациентов

        List<Entity.Room> rooms = new List<Entity.Room>();

        public FormMain()
        {
            InitializeComponent();
        }

        private void buttonAdd_Click(object sender, EventArgs e)
        {
            // Создание экземпляра формы добавления пациента
            FormAddPatient addPatientForm = new FormAddPatient();

            // Отображение формы как модального диалога
            DialogResult result = addPatientForm.ShowDialog();

            // Проверка результата диалога
            if (result == DialogResult.OK)
            {
                // Получение данных о новом пациенте из формы
                string name = addPatientForm.Name;
                int age = addPatientForm.Age;
                int roomId = addPatientForm.RoomId;

                // Создание нового пациента и добавление его в список
                richTextBoxDtos.Add(new Dto.RichTextBoxDto
                {
                    Id = patientIdCounter++,
                    Name = name,
                    Age = age,
                    RoomId = roomId
                }); ;

                // Обновление RichTextBox
                UpdateRichTextBox();
            }
            else
            {
                // Действия, если диалог был отменен или закрыт без сохранения данных
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
            MessageBox.Show("Данные успешно сохранены в формате XML");
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
                        MessageBox.Show("Не удалось загрузить XML-файл. Пожалуйста, попробуйте, очистить список.");
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
                MessageBox.Show("XML-файл не найден");

            }
            UpdateRichTextBox();
        }

        private void buttonFindLinq_Click(object sender, EventArgs e)
        {
            // Отображение диалогового окна для ввода возраста
            string input = Microsoft.VisualBasic.Interaction.InputBox("Введите возраст:", "Ввод возраста", "");

            // Проверка, было ли введено значение
            if (!string.IsNullOrEmpty(input))
            {
                // Попытка преобразовать введенное значение в число
                if (int.TryParse(input, out int age))
                {
                    // Фильтрация пациентов по введенному возрасту
                    var olderPatients = richTextBoxDtos.Where(patient => patient.Age > age).ToList();

                    // Обновление данных в RichTextBox
                    richTextBoxDtos = olderPatients;
                    UpdateRichTextBox();
                }
                else
                {
                    MessageBox.Show("Некорректный ввод. Пожалуйста, введите числовое значение.");
                }
            }
        }

        private void buttonFindLambda_Click(object sender, EventArgs e)
        {
            richTextBoxDtos = richTextBoxDtos
                                .GroupBy(patient => patient.RoomId)
                                .OrderBy(room => room.Key) // Отсортировать по RoomId по возрастанию
                                .SelectMany(elements => elements.ToList()) // Преобразует последовательность элементов в одну последовательность
                                .ToList();

            var roomWithMostPatients = richTextBoxDtos
                                        .GroupBy(patient => patient.RoomId)
                                        .Select(group => new { RoomId = group.Key, Count = group.Count() })
                                        .OrderByDescending(x => x.Count).FirstOrDefault();
            if (roomWithMostPatients != null)
            {
                MessageBox.Show($"Комната {roomWithMostPatients.RoomId} имеет наибольшее количество пациентов: {roomWithMostPatients.Count}");
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
            // Если список комнат пуст, создайте начальные комнаты
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

            // Перемешайте список пациентов
            patients = patients.OrderBy(p => random.Next()).ToList();

            // Равномерно распределите пациентов по существующим комнатам
            foreach (var patient in patients)
            {
                int roomIndex = (int)random.NextInt64(1, numberOfRooms);

                // Добавьте пациента в текущую комнату
                rooms[roomIndex].Patients.Add(patient);
                // Привяжите пациента к текущей комнате
                patient.RoomId = rooms[roomIndex].Id;

                // Перейдите к следующей комнате или вернитесь к первой, если достигнут конец списка комнат
                roomIndex = (roomIndex + 1) % rooms.Count;
            }
        }
    }
}

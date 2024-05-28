namespace Patient
{
    public partial class FormAddPatient : Form
    {
        // Свойства для доступа к данным, введенным пользователем (например, Name, Age, RoomId)
        public string Name => nameTextBox.Text;
        public int Age => (int)ageNumericUpDown.Value;
        public int RoomId => (int)numericUpDownRoomId.Value;

        public FormAddPatient()
        {
            InitializeComponent();
        }

        private void AddButton_Click(object sender, EventArgs e)
        {
            // Проверка валидности введенных данных перед закрытием формы
            if (string.IsNullOrWhiteSpace(Name))
            {
                MessageBox.Show("Введите имя пациента.", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if (Age <= 0 || Age > 120)
            {
                MessageBox.Show("Введите корректный возраст пациента.", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if (RoomId <= 0)
            {
                MessageBox.Show("Введите корректный номер палаты.", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            // Если все данные введены корректно, закрываем форму с результатом DialogResult.OK
            DialogResult = DialogResult.OK;
            Close();
        }

        private void CancelButton_Click(object sender, EventArgs e)
        {
            // Закрытие формы при нажатии на кнопку "Отмена" с результатом DialogResult.Cancel
            DialogResult = DialogResult.Cancel;
            Close();
        }
    }
}

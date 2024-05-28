namespace Patient
{
    partial class FormAddPatient
    {
        private Label nameLabel;
        private TextBox nameTextBox;
        private Label ageLabel;
        private Label roomIdLabel;
        private Button addButton;
        private Button cancelButton;

        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            nameLabel = new Label();
            nameTextBox = new TextBox();
            ageLabel = new Label();
            roomIdLabel = new Label();
            addButton = new Button();
            cancelButton = new Button();
            ageNumericUpDown = new NumericUpDown();
            numericUpDownRoomId = new NumericUpDown();
            ((System.ComponentModel.ISupportInitialize)ageNumericUpDown).BeginInit();
            ((System.ComponentModel.ISupportInitialize)numericUpDownRoomId).BeginInit();
            SuspendLayout();
            // 
            // nameLabel
            // 
            nameLabel.Location = new Point(10, 10);
            nameLabel.Name = "nameLabel";
            nameLabel.Size = new Size(100, 23);
            nameLabel.TabIndex = 0;
            nameLabel.Text = "Имя:";
            // 
            // nameTextBox
            // 
            nameTextBox.Location = new Point(116, 12);
            nameTextBox.Name = "nameTextBox";
            nameTextBox.Size = new Size(184, 23);
            nameTextBox.TabIndex = 1;
            // 
            // ageLabel
            // 
            ageLabel.Location = new Point(10, 40);
            ageLabel.Name = "ageLabel";
            ageLabel.Size = new Size(100, 23);
            ageLabel.TabIndex = 2;
            ageLabel.Text = "Возраст:";
            // 
            // roomIdLabel
            // 
            roomIdLabel.Location = new Point(10, 70);
            roomIdLabel.Name = "roomIdLabel";
            roomIdLabel.Size = new Size(100, 23);
            roomIdLabel.TabIndex = 4;
            roomIdLabel.Text = "Номер палаты:";
            // 
            // addButton
            // 
            addButton.Location = new Point(225, 100);
            addButton.Name = "addButton";
            addButton.Size = new Size(75, 23);
            addButton.TabIndex = 6;
            addButton.Text = "Добавить";
            addButton.Click += AddButton_Click;
            // 
            // cancelButton
            // 
            cancelButton.Location = new Point(144, 100);
            cancelButton.Name = "cancelButton";
            cancelButton.Size = new Size(75, 23);
            cancelButton.TabIndex = 7;
            cancelButton.Text = "Отмена";
            cancelButton.Click += CancelButton_Click;
            // 
            // ageNumericUpDown
            // 
            ageNumericUpDown.Location = new Point(116, 38);
            ageNumericUpDown.Maximum = new decimal(new int[] { 120, 0, 0, 0 });
            ageNumericUpDown.Name = "ageNumericUpDown";
            ageNumericUpDown.Size = new Size(184, 23);
            ageNumericUpDown.TabIndex = 3;
            // 
            // numericUpDownRoomId
            // 
            numericUpDownRoomId.Location = new Point(116, 66);
            numericUpDownRoomId.Maximum = new decimal(new int[] { 120, 0, 0, 0 });
            numericUpDownRoomId.Name = "numericUpDownRoomId";
            numericUpDownRoomId.Size = new Size(184, 23);
            numericUpDownRoomId.TabIndex = 8;
            // 
            // FormAddPatient
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(312, 149);
            Controls.Add(numericUpDownRoomId);
            Controls.Add(nameLabel);
            Controls.Add(nameTextBox);
            Controls.Add(ageLabel);
            Controls.Add(ageNumericUpDown);
            Controls.Add(roomIdLabel);
            Controls.Add(addButton);
            Controls.Add(cancelButton);
            FormBorderStyle = FormBorderStyle.FixedDialog;
            MaximizeBox = false;
            // Name = "FormAddPatient";
            StartPosition = FormStartPosition.CenterScreen;
            Text = "Добавить пациента";
            ((System.ComponentModel.ISupportInitialize)ageNumericUpDown).EndInit();
            ((System.ComponentModel.ISupportInitialize)numericUpDownRoomId).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private NumericUpDown ageNumericUpDown;
        private NumericUpDown numericUpDownRoomId;
    }
}
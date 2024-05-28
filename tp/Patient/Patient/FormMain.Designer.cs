namespace Patient
{
    partial class FormMain
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
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
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            richTextBox = new RichTextBox();
            buttonFill = new Button();
            buttonSaveToXml = new Button();
            buttonLoadFromXml = new Button();
            buttonFindLinq = new Button();
            buttonClear = new Button();
            buttonFindLambda = new Button();
            buttonAdd = new Button();
            SuspendLayout();
            // 
            // richTextBox
            // 
            richTextBox.Location = new Point(12, 12);
            richTextBox.Name = "richTextBox";
            richTextBox.Size = new Size(704, 276);
            richTextBox.TabIndex = 0;
            richTextBox.Text = "";
            // 
            // buttonFill
            // 
            buttonFill.Location = new Point(12, 335);
            buttonFill.Name = "buttonFill";
            buttonFill.Size = new Size(704, 35);
            buttonFill.TabIndex = 1;
            buttonFill.Text = "Заполнить";
            buttonFill.UseVisualStyleBackColor = true;
            buttonFill.Click += buttonFill_Click;
            // 
            // buttonSaveToXml
            // 
            buttonSaveToXml.Location = new Point(12, 376);
            buttonSaveToXml.Name = "buttonSaveToXml";
            buttonSaveToXml.Size = new Size(704, 35);
            buttonSaveToXml.TabIndex = 2;
            buttonSaveToXml.Text = "Сохранить";
            buttonSaveToXml.UseVisualStyleBackColor = true;
            buttonSaveToXml.Click += buttonSaveToXml_Click;
            // 
            // buttonLoadFromXml
            // 
            buttonLoadFromXml.Location = new Point(12, 417);
            buttonLoadFromXml.Name = "buttonLoadFromXml";
            buttonLoadFromXml.Size = new Size(704, 35);
            buttonLoadFromXml.TabIndex = 3;
            buttonLoadFromXml.Text = "Загрузить";
            buttonLoadFromXml.UseVisualStyleBackColor = true;
            buttonLoadFromXml.Click += buttonLoadFromXml_Click;
            // 
            // buttonFindLinq
            // 
            buttonFindLinq.Location = new Point(12, 458);
            buttonFindLinq.Name = "buttonFindLinq";
            buttonFindLinq.Size = new Size(704, 35);
            buttonFindLinq.TabIndex = 4;
            buttonFindLinq.Text = "LINQ";
            buttonFindLinq.UseVisualStyleBackColor = true;
            buttonFindLinq.Click += buttonFindLinq_Click;
            // 
            // buttonClear
            // 
            buttonClear.Location = new Point(12, 541);
            buttonClear.Name = "buttonClear";
            buttonClear.Size = new Size(704, 35);
            buttonClear.TabIndex = 5;
            buttonClear.Text = "Очистить";
            buttonClear.UseVisualStyleBackColor = true;
            buttonClear.Click += buttonClear_Click;
            // 
            // buttonFindLambda
            // 
            buttonFindLambda.Location = new Point(12, 500);
            buttonFindLambda.Name = "buttonFindLambda";
            buttonFindLambda.Size = new Size(704, 35);
            buttonFindLambda.TabIndex = 6;
            buttonFindLambda.Text = "LAMBDA";
            buttonFindLambda.UseVisualStyleBackColor = true;
            buttonFindLambda.Click += buttonFindLambda_Click;
            // 
            // buttonAdd
            // 
            buttonAdd.Location = new Point(12, 294);
            buttonAdd.Name = "buttonAdd";
            buttonAdd.Size = new Size(704, 35);
            buttonAdd.TabIndex = 7;
            buttonAdd.Text = "Добавить";
            buttonAdd.UseVisualStyleBackColor = true;
            buttonAdd.Click += buttonAdd_Click;
            // 
            // FormMain
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(728, 588);
            Controls.Add(buttonAdd);
            Controls.Add(buttonFindLambda);
            Controls.Add(buttonClear);
            Controls.Add(buttonFindLinq);
            Controls.Add(buttonLoadFromXml);
            Controls.Add(buttonSaveToXml);
            Controls.Add(buttonFill);
            Controls.Add(richTextBox);
            Name = "FormMain";
            Text = "Главная";
            ResumeLayout(false);
        }

        #endregion

        private RichTextBox richTextBox;
        private Button buttonFill;
        private Button buttonSaveToXml;
        private Button buttonLoadFromXml;
        private Button buttonFindLinq;
        private Button buttonClear;
        private Button buttonFindLambda;
        private Button buttonAdd;
    }
}

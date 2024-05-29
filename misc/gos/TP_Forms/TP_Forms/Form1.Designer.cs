namespace TP_Forms;

partial class Form1
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
        buttonCreate = new Button();
        buttonClear = new Button();
        buttonSave = new Button();
        buttonLoad = new Button();
        buttonSelect = new Button();
        SuspendLayout();
        // 
        // richTextBox
        // 
        richTextBox.Location = new Point(8, 10);
        richTextBox.Name = "richTextBox";
        richTextBox.Size = new Size(780, 352);
        richTextBox.TabIndex = 0;
        richTextBox.Text = "";
        // 
        // buttonCreate
        // 
        buttonCreate.Location = new Point(8, 368);
        buttonCreate.Name = "buttonCreate";
        buttonCreate.Size = new Size(780, 23);
        buttonCreate.TabIndex = 1;
        buttonCreate.Text = "Заполнить рандомно";
        buttonCreate.UseVisualStyleBackColor = true;
        buttonCreate.Click += buttonCreate_Click;
        // 
        // buttonClear
        // 
        buttonClear.Location = new Point(8, 397);
        buttonClear.Name = "buttonClear";
        buttonClear.Size = new Size(780, 23);
        buttonClear.TabIndex = 1;
        buttonClear.Text = "Очистить";
        buttonClear.UseVisualStyleBackColor = true;
        buttonClear.Click += buttonClear_Click;
        // 
        // buttonSave
        // 
        buttonSave.Location = new Point(8, 426);
        buttonSave.Name = "buttonSave";
        buttonSave.Size = new Size(780, 23);
        buttonSave.TabIndex = 1;
        buttonSave.Text = "Сохранить в файл";
        buttonSave.UseVisualStyleBackColor = true;
        buttonSave.Click += buttonSave_Click;
        // 
        // buttonLoad
        // 
        buttonLoad.Location = new Point(8, 455);
        buttonLoad.Name = "buttonLoad";
        buttonLoad.Size = new Size(780, 24);
        buttonLoad.TabIndex = 1;
        buttonLoad.Text = "Получить из файла";
        buttonLoad.UseVisualStyleBackColor = true;
        buttonLoad.Click += buttonLoad_Click;
        // 
        // buttonSelect
        // 
        buttonSelect.Location = new Point(8, 485);
        buttonSelect.Name = "buttonSelect";
        buttonSelect.Size = new Size(780, 24);
        buttonSelect.TabIndex = 1;
        buttonSelect.Text = "Выборка из списка";
        buttonSelect.UseVisualStyleBackColor = true;
        buttonSelect.Click += buttonSelect_Click;
        // 
        // Form1
        // 
        AutoScaleDimensions = new SizeF(7F, 15F);
        AutoScaleMode = AutoScaleMode.Font;
        ClientSize = new Size(800, 519);
        Controls.Add(buttonSelect);
        Controls.Add(buttonLoad);
        Controls.Add(buttonSave);
        Controls.Add(buttonClear);
        Controls.Add(buttonCreate);
        Controls.Add(richTextBox);
        Name = "Form1";
        Text = "Form1";
        ResumeLayout(false);
    }

    #endregion

    private RichTextBox richTextBox;
    private Button buttonCreate;
    private Button buttonClear;
    private Button buttonSave;
    private Button buttonLoad;
    private Button buttonSelect;
}

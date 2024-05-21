namespace Windows_Forms
{
    partial class Form1
    {
        private System.ComponentModel.IContainer components = null;

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
            this.AnimalsDataGridView = new System.Windows.Forms.DataGridView();
            this.AnimalCareDataGridView = new System.Windows.Forms.DataGridView();
            this.button1 = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.AnimalsDataGridView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.AnimalCareDataGridView)).BeginInit();
            this.SuspendLayout();
            // 
            // 
            // 
            this.AnimalsDataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.AnimalsDataGridView.Location = new System.Drawing.Point(833, 12);
            this.AnimalsDataGridView.Name = "AnimalsDataGridView";
            this.AnimalsDataGridView.RowHeadersWidth = 51;
            this.AnimalsDataGridView.RowTemplate.Height = 24;
            this.AnimalsDataGridView.Size = new System.Drawing.Size(438, 286);
            this.AnimalsDataGridView.TabIndex = 0;
            // 
            // teamsDataGridView
            // 
            this.AnimalCareDataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.AnimalCareDataGridView.Location = new System.Drawing.Point(302, 12);
            this.AnimalCareDataGridView.Name = "AnimalCareDataGridView";
            this.AnimalCareDataGridView.RowHeadersWidth = 51;
            this.AnimalCareDataGridView.RowTemplate.Height = 24;
            this.AnimalCareDataGridView.Size = new System.Drawing.Size(478, 286);
            this.AnimalCareDataGridView.TabIndex = 1;
            // 
            // button1
            // 
            this.button1.AccessibleDescription = "updateButton";
            this.button1.AccessibleName = "updateButton";
            this.button1.AccessibleRole = System.Windows.Forms.AccessibleRole.None;
            this.button1.Location = new System.Drawing.Point(759, 368);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(103, 50);
            this.button1.TabIndex = 2;
            this.button1.Text = "update";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.updateEvent);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(527, 329);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(50, 16);
            this.label1.TabIndex = 3;
            this.label1.Text = "AnimalCare";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(1035, 329);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(46, 16);
            this.label2.TabIndex = 4;
            this.label2.Text = "Animals";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1717, 716);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.AnimalCareDataGridView);
            this.Controls.Add(this.AnimalsDataGridView);
            this.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.AnimalsDataGridView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.AnimalCareDataGridView)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView AnimalsDataGridView;
        private System.Windows.Forms.DataGridView AnimalCareDataGridView;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
    }
}


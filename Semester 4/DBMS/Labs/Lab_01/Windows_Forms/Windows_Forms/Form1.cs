using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace Windows_Forms
{
    public partial class Form1 : Form
    {
        SqlConnection connection;
        SqlDataAdapter adapterAnimals;
        SqlDataAdapter adapterAnimalCare;

        DataSet dataset;

        BindingSource bindingAnimals;
        BindingSource bindingAnimalCare;

        SqlCommandBuilder commandBuilder;

        string queryAnimals;
        string queryAnimalCare;

        public Form1()
        {
            InitializeComponent();
            FillData();
        }


        string getConnectionString()
        {
            return "Data Source=LAPTOP-OQ89RHJ2\\SQLEXPRESS;" +
                "Initial Catalog=Circ;Integrated Security=true;";
        }


        void FillData()
        {
            connection = new SqlConnection(getConnectionString());

            queryAnimals = "select * from Animals";
            queryAnimalCare = "select * from AnimalCare";

            adapterAnimals = new SqlDataAdapter(queryAnimals, connection);
            adapterAnimalCare = new SqlDataAdapter(queryAnimalCare, connection);

            dataset = new DataSet();

            adapterAnimals.Fill(dataset, "Animals");
            adapterAnimalCare.Fill(dataset, "AnimalCare");

            //fill in insert, update, and delete commands
            commandBuilder = new SqlCommandBuilder(adapterAnimalCare);

            //DataRelation (parent-child rel) added to the dataset
            dataset.Relations.Add(
                "AnimalsAnimalCare",
                dataset.Tables["Animals"].Columns["AnimalID"],
                dataset.Tables["AnimalCare"].Columns["AnimalID"]
                );


            //METH 1: fill data into DataGridViews using properties DataSource, DataMember
           // this.AnimalsDataGridView.DataSource = dataset.Tables["Animals"];
            //this.AnimalCareDataGridView.DataSource = this.AnimalsDataGridView.DataSource;
            //this.AnimalCareDataGridView.DataMember = "AnimalsAnimalCare";

            //METH 2: fill data into DataGridViews using Data Binding
            bindingAnimals = new BindingSource();
            bindingAnimals.DataSource = dataset.Tables["Animals"];
            bindingAnimalCare = new BindingSource(bindingAnimals, "AnimalsAnimalCare");

            this.AnimalsDataGridView.DataSource = bindingAnimals;
            this.AnimalCareDataGridView.DataSource = bindingAnimalCare;

            commandBuilder.GetUpdateCommand();
        }


        private void updateEvent(object sender, EventArgs e)
        {
            try
            {
                adapterAnimalCare.Update(dataset, "AnimalCare");
            }catch(Exception exception)
            {
                MessageBox.Show("Database update did not work\n" + exception.Message, "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                Console.WriteLine(exception.ToString());
            }
        }

    }
}

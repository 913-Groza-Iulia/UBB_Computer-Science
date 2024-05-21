using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Configuration;

namespace Form1
{
    public partial class Form1 : Form
    {
        SqlConnection conn;
        SqlDataAdapter daAnimals;
        SqlDataAdapter daAnimalCare;
        DataSet dset;
        BindingSource bsAnimals;
        BindingSource bsAnimalCare;
        SqlCommandBuilder cmdBuilder;
        string queryAnimals;
        string queryAnimalCare;
        String parentTable;
        String childTable;
        String foreignKey;
        
        public Form1()
        {
            InitializeComponent();
            FillData();
        }
        /*private void Form1_Load(object sender, EventArgs e)
        {
            MessageBox.Show(ConfigurationManager.AppSettings["greeting"]);
        }*/
        void FillData()
        {
            parentTable = ConfigurationManager.AppSettings["Parent"];
            childTable = ConfigurationManager.AppSettings["Child"];
            foreignKey = ConfigurationManager.AppSettings["foreignKey"];

            conn = new SqlConnection(getConnectionString());
            queryAnimals = "SELECT * FROM " + parentTable;
            queryAnimalCare = "SELECT * FROM " + childTable;

            daAnimals=new SqlDataAdapter(queryAnimals, conn);
            daAnimalCare = new SqlDataAdapter(queryAnimalCare, conn);
            dset=new DataSet();
            daAnimals.Fill(dset,parentTable);
            daAnimalCare.Fill(dset, childTable);
            cmdBuilder = new SqlCommandBuilder(daAnimalCare);

            dset.Relations.Add("AnimalsAnimalCare",
                dset.Tables[parentTable].Columns[foreignKey],
                dset.Tables[childTable].Columns[foreignKey]);

            /*  this.AnimalsGridView.DataSource = dset.Tables["Animals"];
              this.AnimalCareGridView.DataSource = this.AnimalsGridView.DataSource;
              this.AnimalCareGridView.DataMember = "AnimalsAnimalCare";
  */

            bsAnimals = new BindingSource();
            bsAnimals.DataSource = dset.Tables[parentTable];
            bsAnimalCare = new BindingSource(bsAnimals, "AnimalsAnimalCare");
            this.animalsDataGridView.DataSource = bsAnimals;
            this.animalCareDataGridView.DataSource = bsAnimalCare;

            cmdBuilder.GetUpdateCommand();

        }

        private void updateEvent(object sender, EventArgs e)
        {
            try
            {
                daAnimalCare.Update(dset, childTable);
            }
            catch (Exception exception)
            {
                MessageBox.Show(exception.Message, "Error",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                Console.WriteLine(exception.ToString());
            }
        }
        /*private void button1_Click(object sender, EventArgs e)
        {
            daAnimalCare.Update(dset, "AnimalCare");
        }*/

        string getConnectionString()
        {
            return "Data Source=LAPTOP-OQ89RHJ2\\SQLEXPRESS;" + "Initial Catalog=Circ;Integrated Security=true";
        }

    }
}

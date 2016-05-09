using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using MegaProductionDBLIB;

namespace MegaProduction
{
    /// <summary>
    /// Logique d'interaction pour InformationDomaineWindow.xaml
    /// </summary>
    public partial class InformationDomaineWindow : Window
    {
        MegaCastingsEntities db = new MegaCastingsEntities();
        public DomaineMetier DomaineMetier { get; set; }

        public InformationDomaineWindow(DomaineMetier domaine,MegaCastingsEntities context)
        {
            InitializeComponent();
            db = context;
            this.DomaineMetier = domaine;
            this.DataContext = this;
        }

        private void BTN_Cancel_Click(object sender, RoutedEventArgs e)
        {
            //Ferme la fenêtre
            Close();
        }

        private void BTN_Ok_Click(object sender, RoutedEventArgs e)
        {
            //Vérifie les champs obligatoires
            if(this.DomaineMetier.Libelle == null)
            {
                MessageBox.Show("Veuillez remplir le libelle");
            }
            else
            {
                this.DialogResult = true;
            }
            
        }
    }
}

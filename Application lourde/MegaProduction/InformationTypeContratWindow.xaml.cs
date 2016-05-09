using MegaProductionDBLIB;
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

namespace MegaProduction
{
    /// <summary>
    /// Logique d'interaction pour InformationTypeContratWindow.xaml
    /// </summary>
    public partial class InformationTypeContratWindow : Window
    {
        MegaCastingsEntities db = new MegaCastingsEntities();
        public TypeContrat TypeContrat { get; set; }

        public InformationTypeContratWindow(TypeContrat typeContrat)
        {
            InitializeComponent();
            this.TypeContrat = typeContrat;
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
            if(this.TypeContrat.Libelle == null)
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

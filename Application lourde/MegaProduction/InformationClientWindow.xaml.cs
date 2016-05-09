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
using System.Collections.ObjectModel;

namespace MegaProduction
{
    /// <summary>
    /// Logique d'interaction pour InformationClientWindow.xaml
    /// </summary>
    public partial class InformationClientWindow : Window
    {
        MegaCastingsEntities db = new MegaCastingsEntities();
        public Client Client { get; set; }
        public ObservableCollection<Pack> Packs { get; set; }

        public InformationClientWindow(Client client, MegaCastingsEntities context)
        {
            InitializeComponent();
            //récupère le client
            this.Client = client;
            db = context;
            this.Packs = new ObservableCollection<Pack>(db.Packs.ToList());
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
            if (this.Client.Libelle == null || this.Client.Siret == null)
            {
                MessageBox.Show("Veuillez remplir les champs obligatoires *");
            }
            else
            {
                this.Client.IsDiffuseur = false;
                this.Client.Pack = listPacks.SelectedItem as Pack;
                this.DialogResult = true;
            }
            
        }
    }
}

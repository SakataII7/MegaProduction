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
    /// Logique d'interaction pour InformationPartenaireWindow.xaml
    /// </summary>
    public partial class InformationPartenaireWindow : Window
    {
        MegaCastingsEntities db = new MegaCastingsEntities();
        public Client Client { get; set; }
        public Connexion Connexion { get; set; }

        public InformationPartenaireWindow(Client client, MegaCastingsEntities context)
        {
            InitializeComponent();
            this.Client = client;
            db = context;
            this.Connexion = new Connexion();
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
            if (this.Client.Libelle == null || this.Client.Siret == null || this.Connexion.Login == null || this.Connexion.Password == null)
            {
                MessageBox.Show("Veuillez remplir les champs obligatoires *");
            }
            else
            {
                this.Client.IsDiffuseur = true;

                //Evite les problèmes lors de la modification
                if (this.Client.IdentifiantConnexion == null)
                {
                    this.Client.IdentifiantConnexion = this.Connexion.Identifiant;
                }
                this.DialogResult = true;
            }
        }
    }
}

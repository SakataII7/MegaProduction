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
    /// Logique d'interaction pour InformationOffreWindow.xaml
    /// </summary>
    public partial class InformationOffreWindow : Window
    {
        MegaCastingsEntities db = new MegaCastingsEntities();
        public Offre Offre { get; set; }
        public ObservableCollection<DomaineMetier> DomaineMetiers { get; set; }
        public ObservableCollection<Metier> Metiers { get; set; }
        public ObservableCollection<TypeContrat> TypeContrats { get; set; }
        public ObservableCollection<Client> Clients { get; set; }
        public ObservableCollection<Client> ClientsNonDiffuseur { get; set; }

        public InformationOffreWindow(Offre offre, MegaCastingsEntities context)
        {
            InitializeComponent();
            this.Offre = offre;
            db = context;
            //Met par défaut la date du jour
            if (this.Offre.DateDebutContrat == new DateTime())
                this.Offre.DateDebutContrat = DateTime.Today;
            //Met par défaut la date du jour
            if (this.Offre.DatePublication == new DateTime())
                this.Offre.DatePublication = DateTime.Today;
            this.DomaineMetiers = new ObservableCollection<DomaineMetier>(db.DomaineMetiers.ToList());
            this.Metiers = new ObservableCollection<Metier>(db.Metiers.ToList());
            this.TypeContrats = new ObservableCollection<TypeContrat>(db.TypeContrats.ToList());
            this.Clients = new ObservableCollection<Client>(db.Clients.ToList());
            List<Client> Client = new List<Client>();

            //Permet de récupérer les clients non partenaires
            foreach (Client client in Clients)
            {
                if (client.IsDiffuseur == false)
                {
                    Client.Add(client);
                }
            }
            ClientsNonDiffuseur = new ObservableCollection<Client>(Client.ToList());
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
            if(this.Offre.Intitule == null || this.Offre.DureeDiffusion == 0 || this.Offre.NbPostes == 0 || this.Offre.DescriptionPoste == null || this.Offre.DescriptionProfil == null)
            {
                MessageBox.Show("Veuillez remplir les champs obligatoires *");
            }
            else
            {
                if(this.Offre.Reference == null)
                {
                    //remplit automatiquement la référence avec un nombre aléatoire
                    Random rand = new Random();
                    this.Offre.Reference = "REF_" + rand.Next(0, 999999999);
                }
                this.DialogResult = true;
            }
        }
    }
}

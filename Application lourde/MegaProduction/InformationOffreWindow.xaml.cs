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
        private MegaCastingsEntities db;
        public Offre Offre { get; set; }
        public ObservableCollection<DomaineMetier> DomaineMetiers { get; set; }
        public ObservableCollection<Metier> Metiers { get; set; }
        public ObservableCollection<TypeContrat> TypeContrats { get; set; }
        public ObservableCollection<Client> Clients { get; set; }

        public InformationOffreWindow(MegaCastingsEntities context ,Offre offre)
        {
            InitializeComponent();
            db = context;
            this.Offre = offre;
            if (this.Offre.DateDebutContrat == new DateTime())
                this.Offre.DateDebutContrat = DateTime.Now;
            if (this.Offre.DatePublication == new DateTime())
                this.Offre.DatePublication = DateTime.Now;
            this.DomaineMetiers = new ObservableCollection<DomaineMetier>(db.DomaineMetiers.ToList());
            this.Metiers = new ObservableCollection<Metier>(db.Metiers.ToList());
            this.TypeContrats = new ObservableCollection<TypeContrat>(db.TypeContrats.ToList());
            this.Clients = new ObservableCollection<Client>(db.Clients.ToList());
            this.DataContext = this;
        }

        private void BTN_Cancel_Click(object sender, RoutedEventArgs e)
        {
            Close();
        }

        private void BTN_Ok_Click(object sender, RoutedEventArgs e)
        {
            if(this.Offre.Intitule == null || this.Offre.DatePublication == null || this.Offre.DureeDiffusion == 0 || this.Offre.NbPostes == 0 || this.Offre.DescriptionPoste == null || this.Offre.DescriptionProfil == null)
            {
                MessageBox.Show("Veuillez remplir les champs obligatoires *");
            }
            else
            {
                if(this.Offre.Reference == null)
                {
                    Random rand = new Random();
                    this.Offre.Reference = "REF_" + rand.Next(0, 999999999) + "_" + this.Offre.Identifiant;
                }
                this.DialogResult = true;
            }
        }
    }
}

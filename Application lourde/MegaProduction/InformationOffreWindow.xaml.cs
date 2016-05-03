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

        public InformationOffreWindow(Offre offre, MegaCastingsEntities context)
        {
            InitializeComponent();
            db = context;
            this.Offre = offre;
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

        }
    }
}

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
using System.Windows.Navigation;
using System.Windows.Shapes;
using MegaProductionDBLIB;

namespace MegaProduction
{
    /// <summary>
    /// Logique d'interaction pour MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        MegaCastingsEntities db = new MegaCastingsEntities();

        public MainWindow()
        {
            InitializeComponent();
        }

        private void Client_Click(object sender, RoutedEventArgs e)
        {
            ClientWindow clientWindow = new ClientWindow(db);
            clientWindow.ShowDialog();
        }

        private void Offre_Click(object sender, RoutedEventArgs e)
        {
            OffreWindow offreWindow = new OffreWindow(db);
            offreWindow.ShowDialog();
        }

        private void Pack_Click(object sender, RoutedEventArgs e)
        {
            PackWindow packWindow = new PackWindow(db);
            packWindow.ShowDialog();
        }

        private void Partenaire_Click(object sender, RoutedEventArgs e)
        {
            PartenaireWindow partenaireWindow = new PartenaireWindow(db);
            partenaireWindow.ShowDialog();
        }

        private void Domaine_Metier_Click(object sender, RoutedEventArgs e)
        {
            DomaineMetierWindow domaineMetierWindow = new DomaineMetierWindow(db);
            domaineMetierWindow.ShowDialog();
        }

        private void Type_Contrat_Click(object sender, RoutedEventArgs e)
        {
            TypeContratWindow typeContratWindow = new TypeContratWindow(db);
            typeContratWindow.ShowDialog();
        }
    }
}

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
    /// Logique d'interaction pour OffreWindow.xaml
    /// </summary>
    public partial class OffreWindow : Window
    {
        private MegaCastingsEntities db;
        public ObservableCollection<Offre> Offres { get; set; }
        public Offre offre { get; set; }

        public OffreWindow(MegaCastingsEntities context)
        {
            InitializeComponent();
            db = context;
            this.DataContext = this;
        }

        private void btn_Ajouter_Click(object sender, RoutedEventArgs e)
        {
            offre = new Offre();
            InformationOffreWindow informationOffreWindow = new InformationOffreWindow(offre,db);

            if (informationOffreWindow.ShowDialog() == true)
            {
                db.Offres.Add(informationOffreWindow.Offre);
                this.Offres.Add(informationOffreWindow.Offre);
                db.SaveChanges();

            }
        }

        private void btn_Modifier_Click(object sender, RoutedEventArgs e)
        {

        }

        private void btn_Supprimer_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}

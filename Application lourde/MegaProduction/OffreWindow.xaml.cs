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
        MegaCastingsEntities db = new MegaCastingsEntities();
        public ObservableCollection<Offre> Offres { get; set; }
        public Offre offre { get; set; }

        public OffreWindow(MegaCastingsEntities context)
        {
            InitializeComponent();
            db = context;
            this.Offres = new ObservableCollection<Offre>(db.Offres.ToList());
            this.DataContext = this;
        }

        private void btn_Ajouter_Click(object sender, RoutedEventArgs e)
        {
            offre = new Offre();
            InformationOffreWindow informationOffreWindow = new InformationOffreWindow(offre,db);

            if (informationOffreWindow.ShowDialog() == true)
            {
                //Ajout de l'offre en base de données
                db.Offres.Add(informationOffreWindow.Offre);
                //Ajout de l'offre dans la liste
                this.Offres.Add(informationOffreWindow.Offre);
                //Sauvegarde les changements
                db.SaveChanges();
            }
        }

        private void btn_Modifier_Click(object sender, RoutedEventArgs e)
        {
            if (listOffres.SelectedItem != null)
            {
                //Prend l'offre sélectionnée
                offre = listOffres.SelectedItem as Offre;
                //Prend l'index de l'offre sélectionnée
                int currentIndex = listOffres.SelectedIndex;
                InformationOffreWindow informationOffreWindow = new InformationOffreWindow(offre,db);

                if (informationOffreWindow.ShowDialog() == true)
                {
                    //sauvegarde les changements
                    db.SaveChanges();
                }
            }
        }

        private void btn_Supprimer_Click(object sender, RoutedEventArgs e)
        {
            if (listOffres.SelectedItem != null)
            {
                //Prend l'offre sélectionnée
                offre = listOffres.SelectedItem as Offre;
                //Prend l'index de l'offre sélectionnée
                int currentIndex = listOffres.SelectedIndex;
                //Supprime l'offre dans la base de données
                db.Offres.Remove(offre);
                //Supprime l'offre dans liste
                this.Offres.Remove(offre);
                listOffres.SelectedIndex = currentIndex;
                listOffres.Focus();
                //Sauvegarde les changements
                db.SaveChanges();
            }
        }
    }
}

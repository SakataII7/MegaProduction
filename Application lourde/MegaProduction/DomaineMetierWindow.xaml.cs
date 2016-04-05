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
    /// Logique d'interaction pour DomaineMetierWindow.xaml
    /// </summary>
    public partial class DomaineMetierWindow : Window
    {
        MegaCastingsEntities db = new MegaCastingsEntities();
        public ObservableCollection<DomaineMetier> DomaineMetiers { get; set; }
        public ObservableCollection<Metier> Metiers { get; set; }

        public DomaineMetierWindow(MegaCastingsEntities context)
        {
            InitializeComponent();
            db = context;
            this.DomaineMetiers = new ObservableCollection<DomaineMetier>(db.DomaineMetiers.ToList());
            this.Metiers = new ObservableCollection<Metier>(db.Metiers.ToList());
            this.DataContext = this;
        }

        private void btn_Ajouter_Domaine_Click(object sender, RoutedEventArgs e)
        {
            InformationDomaineWindow informationDomaineWindow = new InformationDomaineWindow(db);

            if (informationDomaineWindow.ShowDialog() == true)
            {
                db.DomaineMetiers.Add(informationDomaineWindow.DomaineMetier);
                this.DomaineMetiers.Add(informationDomaineWindow.DomaineMetier);
                db.SaveChanges();
            }
        }

        private void btn_Modifier_Domaine_Click(object sender, RoutedEventArgs e)
        {
            
        }

        private void btn_Supprimer_Domaine_Click(object sender, RoutedEventArgs e)
        {
            if (listDomaines.SelectedItem != null)
            {
                DomaineMetier domaine = listDomaines.SelectedItem as DomaineMetier;
                int currentIndex = listDomaines.SelectedIndex;
                db.DomaineMetiers.Remove(domaine);
                this.DomaineMetiers.Remove(domaine);
                listDomaines.SelectedIndex = currentIndex;
                listDomaines.Focus();
                db.SaveChanges();
            }
        }

        private void btn_Ajouter_Metier_Click(object sender, RoutedEventArgs e)
        {
            InformationMetierWindow informationMetierWindow = new InformationMetierWindow(db);

            if (informationMetierWindow.ShowDialog() == true)
            {
                db.Metiers.Add(informationMetierWindow.Metier);
                this.Metiers.Add(informationMetierWindow.Metier);
               
                db.SaveChanges();
            }
        }

        private void btn_Modifier_Metier_Click(object sender, RoutedEventArgs e)
        {

        }

        private void btn_Supprimer_Metier_Click(object sender, RoutedEventArgs e)
        {
            if (listMetiers.SelectedItem != null)
            {
                Metier metier = listMetiers.SelectedItem as Metier;
                int currentIndex = listMetiers.SelectedIndex;
                db.Metiers.Remove(metier);
                this.Metiers.Remove(metier);
                listMetiers.SelectedIndex = currentIndex;
                listMetiers.Focus();
                db.SaveChanges();
            }
        }
    }
}

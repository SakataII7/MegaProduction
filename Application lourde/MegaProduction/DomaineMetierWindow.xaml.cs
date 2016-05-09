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
        public DomaineMetier domaine { get; set; }
        public Metier metier { get; set; }

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
            domaine = new DomaineMetier();
            InformationDomaineWindow informationDomaineWindow = new InformationDomaineWindow(domaine,db);

            if (informationDomaineWindow.ShowDialog() == true)
            {
                //Ajout du domaine en base de données
                db.DomaineMetiers.Add(informationDomaineWindow.DomaineMetier);
                //Ajout du domaine dans la liste
                this.DomaineMetiers.Add(informationDomaineWindow.DomaineMetier);
                //Sauvegarde les changements
                db.SaveChanges();
            }
        }

        private void btn_Modifier_Domaine_Click(object sender, RoutedEventArgs e)
        {
            if (listDomaines.SelectedItem != null)
            {
                //Prend le domaine sélectionné
                domaine = listDomaines.SelectedItem as DomaineMetier;
                //Prend l'index du domaine sélectionné
                int currentIndex = listDomaines.SelectedIndex;
                InformationDomaineWindow informationDomaineWindow = new InformationDomaineWindow(domaine,db);

                if (informationDomaineWindow.ShowDialog() == true)
                {
                    //Sauvegarde les changements
                    db.SaveChanges();
                }
            }
        }

        private void btn_Supprimer_Domaine_Click(object sender, RoutedEventArgs e)
        {
            if (listDomaines.SelectedItem != null)
            {
                //Prend le domaine sélectionné
                domaine = listDomaines.SelectedItem as DomaineMetier;
                //Prend l'index du domaine sélectionné
                int currentIndex = listDomaines.SelectedIndex;
                //Supprime le domaine dans la base de données
                db.DomaineMetiers.Remove(domaine);
                //Supprime le domaine dans la liste
                this.DomaineMetiers.Remove(domaine);
                listDomaines.SelectedIndex = currentIndex;
                listDomaines.Focus();
                //Sauvegarde les changements
                db.SaveChanges();
            }
        }

        private void btn_Ajouter_Metier_Click(object sender, RoutedEventArgs e)
        {
            metier = new Metier();
            InformationMetierWindow informationMetierWindow = new InformationMetierWindow(metier,db);

            if (informationMetierWindow.ShowDialog() == true)
            {
                //Ajout du métier en base de données
                db.Metiers.Add(informationMetierWindow.Metier);
                //Ajout du métier dans la liste
                this.Metiers.Add(informationMetierWindow.Metier);
                //Sauvegarde les changements
                db.SaveChanges();
            }
        }

        private void btn_Modifier_Metier_Click(object sender, RoutedEventArgs e)
        {
            if (listMetiers.SelectedItem != null)
            {
                //Prend le métier sélectionné
                metier = listMetiers.SelectedItem as Metier;
                //Prend l'index du métier sélectionné
                int currentIndex = listMetiers.SelectedIndex;
                InformationMetierWindow informationMetierWindow = new InformationMetierWindow(metier,db);

                if (informationMetierWindow.ShowDialog() == true)
                {
                    //Sauvegarde les changements
                    db.SaveChanges();
                }
            }
        }

        private void btn_Supprimer_Metier_Click(object sender, RoutedEventArgs e)
        {
            if (listMetiers.SelectedItem != null)
            {
                //Prend le métier sélectionné
                metier = listMetiers.SelectedItem as Metier;
                //Prend l'index du métier sélectionné
                int currentIndex = listMetiers.SelectedIndex;
                //Supprime le métier dans la base de données
                db.Metiers.Remove(metier);
                //Supprime le métier dans la liste
                this.Metiers.Remove(metier);
                listMetiers.SelectedIndex = currentIndex;
                listMetiers.Focus();
                //Sauvegarde les changements
                db.SaveChanges();
            }
        }
    }
}

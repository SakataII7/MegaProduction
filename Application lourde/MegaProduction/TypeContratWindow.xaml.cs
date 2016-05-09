using MegaProductionDBLIB;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
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

namespace MegaProduction
{
    /// <summary>
    /// Logique d'interaction pour TypeContratWindow.xaml
    /// </summary>
    public partial class TypeContratWindow : Window
    {
        MegaCastingsEntities db = new MegaCastingsEntities();
        public ObservableCollection<TypeContrat> TypeContrats { get; set; }
        public TypeContrat typeContrat { get; set; }

        public TypeContratWindow(MegaCastingsEntities context)
        {
            InitializeComponent();
            db = context;
            this.TypeContrats = new ObservableCollection<TypeContrat>(db.TypeContrats.ToList());
            this.DataContext = this;
        }

        private void btn_Ajouter_Click(object sender, RoutedEventArgs e)
        {
            typeContrat = new TypeContrat();
            InformationTypeContratWindow informationTypeContratWindow = new InformationTypeContratWindow(typeContrat);

            if (informationTypeContratWindow.ShowDialog() == true)
            {
                //Ajout du type contrat en base de données
                db.TypeContrats.Add(informationTypeContratWindow.TypeContrat);
                //Ajout du type contrat dans la liste
                this.TypeContrats.Add(informationTypeContratWindow.TypeContrat);
                //Sauvegarde les changements
                db.SaveChanges();
            }
        }

        private void btn_Modifier_Click(object sender, RoutedEventArgs e)
        {
            if (listTypeContrats.SelectedItem != null)
            {
                //Prend le type contrat sélectionné
                typeContrat = listTypeContrats.SelectedItem as TypeContrat;
                //Prend l'index du type contrat sélectionné
                int currentIndex = listTypeContrats.SelectedIndex;
                InformationTypeContratWindow informationTypeContratWindow = new InformationTypeContratWindow(typeContrat);

                if (informationTypeContratWindow.ShowDialog() == true)
                {
                    //Sauvegarde les changements
                    db.SaveChanges();
                }
            }
        }

        private void btn_Supprimer_Click(object sender, RoutedEventArgs e)
        {
            if (listTypeContrats.SelectedItem != null)
            {
                //Prend le type contrat sélectionné
                typeContrat = listTypeContrats.SelectedItem as TypeContrat;
                //Prend l'index du type contrat sélectionné
                int currentIndex = listTypeContrats.SelectedIndex;
                //Supprime le type contrat dans la base de données
                db.TypeContrats.Remove(typeContrat);
                // Supprime le type contrat dans liste
                this.TypeContrats.Remove(typeContrat);
                listTypeContrats.SelectedIndex = currentIndex;
                listTypeContrats.Focus();
                //Sauvegarde les changements
                db.SaveChanges();
            }
        }
    }
}

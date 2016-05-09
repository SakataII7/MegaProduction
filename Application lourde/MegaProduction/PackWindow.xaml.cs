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
    /// Logique d'interaction pour PackWindow.xaml
    /// </summary>
    public partial class PackWindow : Window
    {
        MegaCastingsEntities db = new MegaCastingsEntities();
        public ObservableCollection<Pack> Packs { get; set; }
        public Pack pack { get; set; }

        public PackWindow(MegaCastingsEntities context)
        {
            InitializeComponent();
            db = context;
            this.Packs = new ObservableCollection<Pack>(db.Packs.ToList());
            this.DataContext = this;
        }

        private void btn_Ajouter_Click(object sender, RoutedEventArgs e)
        {
            pack = new Pack();
            InformationPackWindow informationPackWindow = new InformationPackWindow(pack);

            if (informationPackWindow.ShowDialog() == true)
            {
                //Ajout du pack en base de données
                db.Packs.Add(informationPackWindow.Pack);
                //Ajout du pack dans la liste
                this.Packs.Add(informationPackWindow.Pack);
                //Sauvegarde les changements
                db.SaveChanges();
                
            }
        }

        private void btn_Supprimer_Click(object sender, RoutedEventArgs e)
        {
            if (listPacks.SelectedItem != null)
            {
                //Prend le pack sélectionné
                pack = listPacks.SelectedItem as Pack;
                //Prend l'index du pack sélectionné
                int currentIndex = listPacks.SelectedIndex;
                //Supprime le pack dans la base de données
                db.Packs.Remove(pack);
                //Supprime le pack dans la liste
                this.Packs.Remove(pack);
                listPacks.SelectedIndex = currentIndex;
                listPacks.Focus();
                //Sauvegarde les changements
                db.SaveChanges();
            }
        }

        private void btn_Modifier_Click(object sender, RoutedEventArgs e)
        {
            if (listPacks.SelectedItem != null)
            {
                //Prend le pack sélectionné
                pack = listPacks.SelectedItem as Pack;
                //Prend l'index du pack sélectionné
                int currentIndex = listPacks.SelectedIndex;
                InformationPackWindow informationPackWindow = new InformationPackWindow(pack);

                if (informationPackWindow.ShowDialog() == true)
                {
                    //Sauvegarde les changements
                    db.SaveChanges();
                }
            }
        }

    }
}

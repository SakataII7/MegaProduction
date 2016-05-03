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
        private MegaCastingsEntities db;
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
                db.Packs.Add(informationPackWindow.Pack);
                this.Packs.Add(informationPackWindow.Pack);
                db.SaveChanges();
                
            }
        }

        private void btn_Supprimer_Click(object sender, RoutedEventArgs e)
        {
            if (listPacks.SelectedItem != null)
            {
                pack = listPacks.SelectedItem as Pack;
                int currentIndex = listPacks.SelectedIndex;
                db.Packs.Remove(pack);
                this.Packs.Remove(pack);
                listPacks.SelectedIndex = currentIndex;
                listPacks.Focus();
                db.SaveChanges();
            }
        }

        private void btn_Modifier_Click(object sender, RoutedEventArgs e)
        {
            pack = listPacks.SelectedItem as Pack;
            int currentIndex = listPacks.SelectedIndex;
            InformationPackWindow informationPackWindow = new InformationPackWindow(pack);

            if (informationPackWindow.ShowDialog() == true)
            {
                db.SaveChanges();
            }
        }

    }
}

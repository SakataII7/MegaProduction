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
    /// Logique d'interaction pour InformationClientWindow.xaml
    /// </summary>
    public partial class InformationClientWindow : Window
    {
        private MegaCastingsEntities db;
        public Client Client { get; set; }
        public ObservableCollection<Pack> Packs { get; set; }

        public InformationClientWindow(MegaCastingsEntities context)
        {
            InitializeComponent();
            db = context;
            this.Client = new Client();
            this.Packs = new ObservableCollection<Pack>(db.Packs.ToList());
            this.DataContext = this;
       
        }

        private void BTN_Cancel_Click(object sender, RoutedEventArgs e)
        {
            Close();
        }

        private void BTN_Ok_Click(object sender, RoutedEventArgs e)
        {
            this.Client.IsDiffuseur = false;

            this.Client.Pack = listPacks.SelectedItem as Pack;
            this.DialogResult = true;
        }
    }
}

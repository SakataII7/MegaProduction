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
    /// Logique d'interaction pour ClientWindow.xaml
    /// </summary>
    public partial class ClientWindow : Window
    {
        MegaCastingsEntities1 db = new MegaCastingsEntities1();
        public ObservableCollection<Client> Clients { get; set; }

        public ClientWindow(MegaCastingsEntities1 context)
        {
            InitializeComponent();
            this.Clients = new ObservableCollection<Client>(db.Clients.ToList());
            this.DataContext = this;
        }

        private void MN_Ajouter_Click(object sender, RoutedEventArgs e)
        {
            InformationClientWindow informationClientWindow = new InformationClientWindow(db);

            if (informationClientWindow.ShowDialog() == true)
            {
                db.Clients.Add(informationClientWindow.Client);
                db.SaveChanges();
            }
        }

        private void MN_Modifier_Click(object sender, RoutedEventArgs e)
        {

        }

        private void MN_Supprimer_Click(object sender, RoutedEventArgs e)
        {
            if (listClients.SelectedItem != null)
            {
                Client client = listClients.SelectedItem as Client;
                int currentIndex = listClients.SelectedIndex;
                db.Clients.Remove(client);
                this.Clients.Remove(client);
                listClients.SelectedIndex = currentIndex;
                listClients.Focus();
                db.SaveChanges();
            }
        }

        private void MN_Fermer_Click(object sender, RoutedEventArgs e)
        {
            Close();
        }
    }
}

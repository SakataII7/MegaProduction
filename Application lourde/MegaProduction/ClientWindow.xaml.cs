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
        MegaCastingsEntities db = new MegaCastingsEntities();
        public ObservableCollection<Client> Clients { get; set; }
        public ObservableCollection<Client> ClientsNonDiffuseur { get; set; }
        public ObservableCollection<Pack> Packs { get; set; }
        public Client client { get; set; }

        public ClientWindow(MegaCastingsEntities context)
        {
            InitializeComponent();
            db = context;
            this.Clients = new ObservableCollection<Client>(db.Clients.ToList());
            this.Packs = new ObservableCollection<Pack>(db.Packs.ToList());
            List<Client> Client = new List<Client>();

            foreach (Client client in Clients)
            {
                if (client.IsDiffuseur == false)
                {
                    Client.Add(client);
                }
            }

            ClientsNonDiffuseur = new ObservableCollection<Client>(Client.ToList());

            this.DataContext = this;
        }

        private void btn_Ajouter_Click(object sender, RoutedEventArgs e)
        {
            client = new Client();
            InformationClientWindow informationClientWindow = new InformationClientWindow(client, db);

            if (informationClientWindow.ShowDialog() == true)
            {
                db.Clients.Add(informationClientWindow.Client);
                this.ClientsNonDiffuseur.Add(informationClientWindow.Client);
                db.SaveChanges();
            }
        }

        private void btn_Modifier_Click(object sender, RoutedEventArgs e)
        {
            client = listClients.SelectedItem as Client;
            int currentIndex = listClients.SelectedIndex;
            InformationClientWindow informationClientWindow = new InformationClientWindow(client,db);

            if (informationClientWindow.ShowDialog() == true)
            {
                db.SaveChanges();
            }
        }

        private void btn_Supprimer_Click(object sender, RoutedEventArgs e)
        {
            if (listClients.SelectedItem != null)
            {
                client = listClients.SelectedItem as Client;
                int currentIndex = listClients.SelectedIndex;
                db.Clients.Remove(client);
                this.ClientsNonDiffuseur.Remove(client);
                listClients.SelectedIndex = currentIndex;
                listClients.Focus();
                db.SaveChanges();
            }
        }
    }
}

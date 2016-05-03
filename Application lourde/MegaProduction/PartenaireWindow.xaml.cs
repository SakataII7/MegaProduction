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
    /// Logique d'interaction pour PartenaireWindow.xaml
    /// </summary>
    public partial class PartenaireWindow : Window
    {
        MegaCastingsEntities db = new MegaCastingsEntities();
        public ObservableCollection<Client> Clients { get; set; }
        public ObservableCollection<Client> ClientsDiffuseur { get; set; }
        public Client client { get; set; }

        public PartenaireWindow(MegaCastingsEntities context)
        {
            InitializeComponent();
            db = context;
            this.Clients = new ObservableCollection<Client>(db.Clients.ToList());
            List<Client> Client = new List<Client>();

            foreach (Client client in Clients)
            {
                if (client.IsDiffuseur == true)
                {
                    Client.Add(client);
                }
            }

            ClientsDiffuseur = new ObservableCollection<Client>(Client.ToList());

            this.DataContext = this;
        }

        private void btn_Ajouter_Click(object sender, RoutedEventArgs e)
        {
            client = new Client();
            InformationPartenaireWindow informationPartenaireWindow = new InformationPartenaireWindow(client,db);

            if (informationPartenaireWindow.ShowDialog() == true)
            {
                db.Clients.Add(informationPartenaireWindow.Client);
                db.Connexions.Add(informationPartenaireWindow.Connexion);
                this.ClientsDiffuseur.Add(informationPartenaireWindow.Client);
                db.SaveChanges();
            }
        }

        private void btn_Modifier_Click(object sender, RoutedEventArgs e)
        {
            client = listClients.SelectedItem as Client;
            int currentIndex = listClients.SelectedIndex;
            InformationPartenaireWindow informationPartenaireWindow = new InformationPartenaireWindow(client, db);

            if (informationPartenaireWindow.ShowDialog() == true)
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
                this.ClientsDiffuseur.Remove(client);
                listClients.SelectedIndex = currentIndex;
                listClients.Focus();
                db.SaveChanges();
            }
        }
    }
}

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

            //Permet de récupérer les clients non partenaires
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
            InformationClientWindow informationClientWindow = new InformationClientWindow(client,db);

            if (informationClientWindow.ShowDialog() == true)
            {
                //Ajout du client dans la base
                db.Clients.Add(informationClientWindow.Client);
                //Ajout du client dans la liste
                this.ClientsNonDiffuseur.Add(informationClientWindow.Client);
                //Sauvegarde les modifications en base de données
                db.SaveChanges();
            }
        }

        private void btn_Modifier_Click(object sender, RoutedEventArgs e)
        {
            if (listClients.SelectedItem != null)
            {
                //Prend le client sélectionné
                client = listClients.SelectedItem as Client;
                //Prend l'index du client sélectionné
                int currentIndex = listClients.SelectedIndex;
                InformationClientWindow informationClientWindow = new InformationClientWindow(client,db);

                if (informationClientWindow.ShowDialog() == true)
                {
                    //Sauvegarde les modifications en base de données
                    db.SaveChanges();
                }
            }   
        }

        private void btn_Supprimer_Click(object sender, RoutedEventArgs e)
        {
            if (listClients.SelectedItem != null)
            {
                //Prend le client sélectionné
                client = listClients.SelectedItem as Client;
                //Prend l'index du client sélectionné
                int currentIndex = listClients.SelectedIndex;
                //Supprime le client en base de données
                db.Clients.Remove(client);
                //Supprime le client dans la liste
                this.ClientsNonDiffuseur.Remove(client);
                listClients.SelectedIndex = currentIndex;
                listClients.Focus();
                //Sauvegarde les modifications en base de données
                db.SaveChanges();
            }
        }
    }
}

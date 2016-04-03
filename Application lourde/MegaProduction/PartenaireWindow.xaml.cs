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

namespace MegaProduction
{
    /// <summary>
    /// Logique d'interaction pour PartenaireWindow.xaml
    /// </summary>
    public partial class PartenaireWindow : Window
    {
        MegaCastingsEntities1 db = new MegaCastingsEntities1();

        public PartenaireWindow(MegaCastingsEntities1 context)
        {
            InitializeComponent();
        }

        private void MN_Ajouter_Click(object sender, RoutedEventArgs e)
        {
            InformationPartenaireWindow informationPartenaireWindow = new InformationPartenaireWindow(db);

            if (informationPartenaireWindow.ShowDialog() == true)
            {
                db.Clients.Add(informationPartenaireWindow.Client);
                db.SaveChanges();
            }
        }

        private void MN_Modifier_Click(object sender, RoutedEventArgs e)
        {

        }

        private void MN_Supprimer_Click(object sender, RoutedEventArgs e)
        {

        }

        private void MN_Fermer_Click(object sender, RoutedEventArgs e)
        {
            Close();
        }
    }
}
